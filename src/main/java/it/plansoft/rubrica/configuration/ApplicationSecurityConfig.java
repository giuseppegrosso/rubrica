package it.plansoft.rubrica.configuration;

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
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // white list
                .antMatchers("/", "/index", "/css", "/js/*").permitAll()
                // proteggo le risorse del catalogo rubrica con ruolo admin
                .antMatchers("/rubricavis/**").hasAnyRole("VISUALIZZATORE")

                .antMatchers("/rubrica/**").hasAnyRole("ADMIN", "USER")

                .anyRequest().authenticated().and().httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        // recupero l'utente dal database.
        UserDetails user = User.builder()
                .username("giuseppe")
                .password(passwordEncoder.encode("giuseppe"))
                .roles("ADMIN", "USER") // ROLE_ADMIN, ROLE_USER
                //.authorities("WRITE", "READ")
                .build();

        // recupero l'utente dal database.
        UserDetails user1 = User.builder()
                .username("lorenzo")
                .password(passwordEncoder.encode("lorenzo"))
                .roles("USER") // ROLE_USER
                //.authorities("READ")
                .build();

        // recupero l'utente dal database.
        UserDetails user2 = User.builder()
                .username("daniele")
                .password(passwordEncoder.encode("daniele"))
                .roles("VISUALIZZATORE") // ROLE_VISUALIZZATORE
                //.authorities("READ")
                .build();

        return new InMemoryUserDetailsManager(user, user1, user2);
    }



}
