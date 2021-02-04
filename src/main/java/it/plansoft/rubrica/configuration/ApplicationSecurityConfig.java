package it.plansoft.rubrica.configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;

import it.plansoft.rubrica.configuration.login.ActiveUserStore;
import it.plansoft.rubrica.configuration.login.LoggedUser;
import it.plansoft.rubrica.security.ApplicationUserPermission;
import it.plansoft.rubrica.security.ApplicationUserRole;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // per utilizzo del preAuthorize
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	private UserServiceProvider uservice;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // per abilitarlo basta commentare il codce.
				// altro
				// .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // tipo
				// di implementazione
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

				.anyRequest().authenticated().and()
//				.httpBasic()
				.formLogin()
					.loginPage("/login").permitAll()
					.usernameParameter("user")
					.passwordParameter("pwd")
					.defaultSuccessUrl("/rubrica", true)
					.and().rememberMe()
//		// variante remeber-me
						.tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
						.key("keySecret")
//				// logout
				.and().logout()
					.logoutUrl("/logout")
					.clearAuthentication(true)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID", "remember-me")
					.logoutSuccessUrl("/login");

	}

	/**
	 * intercettazione del successo alla login
	 * 
	 * @author Giuseppe Grosso
	 *
	 */
	@Component("myAuthenticationSuccessHandler")
	public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

		@Autowired
		ActiveUserStore activeUserStore;

		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException {
			HttpSession session = request.getSession(false);
			if (session != null) {
				LoggedUser user = new LoggedUser(authentication.getName(), activeUserStore);
				session.setAttribute("user", user);
			}
		}
	}

//	@Component
//	public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
//
//		@Override
//		public void commence(HttpServletRequest request, HttpServletResponse response,
//				AuthenticationException authException) throws IOException {
//			response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
//			response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
//		}
//
//	}

	@Bean
	public ActiveUserStore activeUserStore() {
		return new ActiveUserStore();
	}

	@Component("myLogoutSuccessHandler")
	public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			HttpSession session = request.getSession();
			if (session != null) {
				session.removeAttribute("user");
			}
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uservice).passwordEncoder(passwordEncoder);
	}

	// implementare una classe custom che extends DaoAutenticationProvider e
	// sovrasrive alcuni metodi
	// di validazione.

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		
//		UserDetails user = userService.loadUserByUsername("giuseppe");
//		UserDetails user1 = userService.loadUserByUsername("daniele");
//		UserDetails user2 = userService.loadUserByUsername("lorenzo");
//		
//		
//		
////		// recupero l'utente dal database.
////		UserDetails user = User.builder()
////				.username("giuseppe")
////				.password(passwordEncoder.encode("giuseppe"))
////				// .roles("ADMIN", "USER") // ROLE_ADMIN, ROLE_USER
//////				.roles(ApplicationUserRole.USER.name(), ApplicationUserRole.ADMIN.name())
//////                .authorities("WRITE", "READ")
////				.authorities(ApplicationUserRole.ADMIN.getGrantedAutorities())
////				.build();
////
////		// recupero l'utente dal database.
////		UserDetails user1 = User.builder().username("lorenzo").password(passwordEncoder.encode("lorenzo"))
////				//.roles(ApplicationUserRole.USER.name()) // ROLE_USER
//////                .authorities("READ")
////				.authorities(ApplicationUserRole.USER.getGrantedAutorities())
////				.build();
////
////		// recupero l'utente dal database.
////		UserDetails user2 = User.builder().username("daniele").password(passwordEncoder.encode("daniele"))
//////				.roles(ApplicationUserRole.VISUALIZZATORE.name()) // ROLE_VISUALIZZATORE
//////                .authorities("READ")
////				.authorities(ApplicationUserRole.VISUALIZZATORE.getGrantedAutorities())
////				.build(); 
//
//		return new InMemoryUserDetailsManager(user, user1, user2);
//	}

}
