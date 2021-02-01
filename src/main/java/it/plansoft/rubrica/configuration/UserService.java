package it.plansoft.rubrica.configuration;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.plansoft.rubrica.model.UserAccount;
import it.plansoft.rubrica.repository.UserAccountRepository;
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserAccountRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {

		Optional<UserAccount> optionalUser = userRepository.findBySso(user);

		return optionalUser.orElseThrow(
				() -> new UsernameNotFoundException(MessageFormat.format("User with name {0} cannot be found.", user)));

	}
}
