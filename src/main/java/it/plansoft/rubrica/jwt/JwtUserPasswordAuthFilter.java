package it.plansoft.rubrica.jwt;/* ggrosso created on 04/02/2021 inside the package - it.plansoft.rubrica.jwt */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * classe di implementazione di verifica  credenziali e
 * generazione del token.
 */
public class JwtUserPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    public JwtUserPasswordAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        try
        {
            UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest = new ObjectMapper().readValue(request.getInputStream(),
                    UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(usernameAndPasswordAuthenticationRequest.getUsername(),
                    usernameAndPasswordAuthenticationRequest.getPassword()
            );

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        return super.attemptAuthentication(request, response);
    }
}
