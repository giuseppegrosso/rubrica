package it.plansoft.rubrica.configuration;

import it.plansoft.rubrica.security.ApplicationUserPermission;
import it.plansoft.rubrica.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
				// white list
				.antMatchers("/", "/index", "/css", "/js/*").permitAll()
				// proteggo le risorse del catalogo rubrica con ruolo admin
//				.antMatchers("/rubrica/**")
//				.hasAnyRole(ApplicationUserRole.USER.name(), ApplicationUserRole.ADMIN.name())
				.antMatchers("/rubricavis/**").hasAnyRole(ApplicationUserRole.VISUALIZZATORE.name())
				.antMatchers(HttpMethod.DELETE, "/rubrica/**")
				.hasAnyAuthority(ApplicationUserPermission.WRITE.getPermission())
				.antMatchers(HttpMethod.POST, "/rubrica/**")
				.hasAnyAuthority(ApplicationUserPermission.WRITE.getPermission())
				.antMatchers(HttpMethod.PUT, "/rubrica/**")
				.hasAnyAuthority(ApplicationUserPermission.WRITE.getPermission())
				.antMatchers(HttpMethod.GET, "/rubrica/**")
				.hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.USER.name())

				.anyRequest().authenticated().and().httpBasic();
		
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		// recupero l'utente dal database.
		UserDetails user = User.builder()
				.username("giuseppe")
				.password(passwordEncoder.encode("giuseppe"))
				// .roles("ADMIN", "USER") // ROLE_ADMIN, ROLE_USER
//				.roles(ApplicationUserRole.USER.name(), ApplicationUserRole.ADMIN.name())
//                .authorities("WRITE", "READ")
				.authorities(ApplicationUserRole.ADMIN.getGrantedAutorities())
				.build();

		// recupero l'utente dal database.
		UserDetails user1 = User.builder().username("lorenzo").password(passwordEncoder.encode("lorenzo"))
				//.roles(ApplicationUserRole.USER.name()) // ROLE_USER
//                .authorities("READ")
				.authorities(ApplicationUserRole.USER.getGrantedAutorities())
				.build();

		// recupero l'utente dal database.
		UserDetails user2 = User.builder().username("daniele").password(passwordEncoder.encode("daniele"))
//				.roles(ApplicationUserRole.VISUALIZZATORE.name()) // ROLE_VISUALIZZATORE
//                .authorities("READ")
				.authorities(ApplicationUserRole.VISUALIZZATORE.getGrantedAutorities())
				.build(); 

		return new InMemoryUserDetailsManager(user, user1, user2);
	}

}
