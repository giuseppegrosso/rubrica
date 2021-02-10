package jwtimpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTUserPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	// recupero i dati iniettandoli dal ApplicationsecurityContext
	private final AuthenticationManager authenticationManager;

	public JWTUserPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			// 1 step recupero delle credenziali
			UserPasswordAuthRequest userRequest = new ObjectMapper().readValue(request.getInputStream(),
					UserPasswordAuthRequest.class);

			Authentication auth = new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
					userRequest.getPassword());

			// validazione delle credenziali
			Authentication autenticate = authenticationManager.authenticate(auth);

			return autenticate;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String secretKey = "generazione_del_token_jwt_molto_importante_custodire_questa_chiave";
		// generazione del token
		String token = Jwts.builder()
				.setSubject(authResult.getName())
				.claim("authorities", authResult.getAuthorities())
				.setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes())).compact();

		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

	}

}
