package it.plansoft.rubrica.jwt;/* ggrosso created on 06/02/2021 inside the package - it.plansoft.rubrica.jwt */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * validazione del token jwt: nota viene eseguito una volta per ogni richiesta
 */
public class JwtValidateTokenFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public JwtValidateTokenFilter(JwtConfig jwtConfig, SecretKey secretKey) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // recupero il token dalla richiesta
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        // controllo che vi sia il token
        if ((authorizationHeader == null || authorizationHeader.isEmpty()) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            // nel caso non ho niente nella header allora rimando  non accettata la richiesta
            filterChain.doFilter(request, response);
            return;
        }
        // chiave precedente

        String token = "";
        try {

            // tolgo dal token il tag Bearer
            token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            Jws<Claims> jws;
            jws = Jwts.parserBuilder()  // (1)
                    .setSigningKey(secretKey)   // (2)
                    .build()                    // (3)
                    .parseClaimsJws(token); // (4)

            Claims claims = jws.getBody();
            // nome inserito nel subject
            String username = claims.getSubject();
            // recupero le authorities come lista di map
            List<Map<String, String>> authorities = (List<Map<String, String>>)claims.get("authorities");

            // converto le authorities in collection di GrantedAuthorities
            Set<SimpleGrantedAuthority> simpleGrantedAuthoritySet = authorities
                    .stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            // genero l'autenticazione a mano.
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthoritySet);

            // aggiungo l'utente come se fosse autenticato
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e)
        {
            throw new IllegalStateException(String.format("token non valido %s", token));
        }

        // per essere sicuri di ripassare la request e response al prossimo filtro.
        filterChain.doFilter(request, response);

    }
}
