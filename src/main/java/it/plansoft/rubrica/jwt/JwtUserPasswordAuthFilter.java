package it.plansoft.rubrica.jwt;/* ggrosso created on 04/02/2021 inside the package - it.plansoft.rubrica.jwt */

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


/**
 * classe di implementazione di verifica  credenziali e
 * generazione del token.
 */
public class JwtUserPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
    // recupero i dati iniettandoli dal ApplicationsecurityContext
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;


    public JwtUserPasswordAuthFilter(AuthenticationManager authenticationManager,
                                     JwtConfig jwtConfig,
                                     SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 1 step validazione delle credenziali.
            // si estrae i dati dalla richiesta e si inseriscono nell'oggetto di request usernameAndPasswordAuthenticationRequest
            UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest = new ObjectMapper().readValue(request.getInputStream(),
                    UsernameAndPasswordAuthenticationRequest.class);

            // recupero la classe di implementazoine del toke per authenticatoin
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    usernameAndPasswordAuthenticationRequest.getUsername(),
                    usernameAndPasswordAuthenticationRequest.getPassword()
            );
            // a questo punto lo passo al manager per la validazione.
            Authentication authenticate = authenticationManager.authenticate(authentication); // verifico le credenziali
            return authenticate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // creazione del token
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // genero il token
        String key = "mySecretPasswordSecuredVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryLong";

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(secretKey)
                .compact();

        // rimando il token in uscita nella header della response.
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }
}
