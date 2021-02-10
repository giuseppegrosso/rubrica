package jwtimpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtValidateTokenFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// recupero del token
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		// Bearer {token}
		// check se esiste il token
		if ((authorizationHeader == null || authorizationHeader.isEmpty())
				|| !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		// validazione del token tramite libreria jwt.
		String token = authorizationHeader.replace("Bearer ", "");

		Jws<Claims> jws;
		String secretKey = "generazione_del_token_jwt_molto_importante_custodire_questa_chiave";
		try {
			jws = Jwts.parserBuilder() // (1)
					.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())) // (2)
					.build() // (3)
					.parseClaimsJws(token); // (4)

			// we can safely trust the JWT
			// recupero dell'utente e dei suoi ruoli / authorities.
			Claims claims = jws.getBody();
			// recupero username
			String username = claims.getSubject(); // qui abbiamo messo l'utente
			// recupero le authorities
			List<Map<String, String>> authorities = (List<Map<String, String>>) claims.get("authorities");

			// converto in simpleGrantedAuthorities
			Set<GrantedAuthority> simpleGrantedAuthorities = authorities.stream()
					.map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());

			// generazione oggetto di autenticazione.
			Authentication auth = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);

			// validazione delle credenziali
			SecurityContextHolder.getContext().setAuthentication(auth);

		} catch (JwtException ex) { // (5)

			throw new IllegalStateException(String.format("token non valido %s", token));
		}

		// essere sicuri di passare la req e resp al filtro successivo
		filterChain.doFilter(request, response);
	}

}
