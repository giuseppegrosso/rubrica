package it.plansoft.rubrica.configuration;

import it.plansoft.rubrica.jwt.JwtConfig;
import it.plansoft.rubrica.jwt.JwtUserPasswordAuthFilter;
import it.plansoft.rubrica.jwt.JwtValidateTokenFilter;
import it.plansoft.rubrica.security.ApplicationUserPermission;
import it.plansoft.rubrica.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // per utilizzo del preAuthorize
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private UserServiceProvider uservice;
	private final SecretKey secretKey;
	private final JwtConfig jwtConfig;

	@Autowired
	public ApplicationSecurityConfig(
			PasswordEncoder passwordEncoder,
			UserServiceProvider uservice,
			SecretKey secretKey,
			JwtConfig jwtConfig) {
		this.passwordEncoder = passwordEncoder;
		this.uservice = uservice;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable() //per abilitarlo basta commentare il codce.
				// filtro per jwt
				// nota l'implementazione di authenticationManager() e' definita in WebSecurityConfigurerAdapter
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // STATELESS
				.and()
				.addFilter(new JwtUserPasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
				// registrazione del filtro di validazione del token dopo il filtro di autenticazione
				.addFilterAfter(new JwtValidateTokenFilter(jwtConfig, secretKey), JwtUserPasswordAuthFilter.class)
				.authorizeRequests()
				// white list
				.antMatchers("/", "/index", "/css", "/js/*").permitAll()
				// proteggo le risorse del catalogo rubrica con ruolo admin
				.antMatchers("/rubricavis/**").hasAnyRole(ApplicationUserRole.VISUALIZZATORE.name())
				.antMatchers(HttpMethod.DELETE, "/rubrica/**")
				.hasAnyAuthority(ApplicationUserPermission.WRITE.getPermission())
				.antMatchers(HttpMethod.POST, "/rubrica/**")
				.hasAnyAuthority(ApplicationUserPermission.WRITE.getPermission())
				.antMatchers(HttpMethod.PUT, "/rubrica/**")
				.hasAnyAuthority(ApplicationUserPermission.WRITE.getPermission())
				.antMatchers(HttpMethod.GET, "/rubrica/**")
				.hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.USER.name())

				.anyRequest().authenticated()
		;
		

	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uservice).passwordEncoder(passwordEncoder);
	}
}
