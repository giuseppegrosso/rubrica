package it.plansoft.rubrica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.plansoft.rubrica.model.UserAccount;

/**
 * repo per utenti account ruoli
 * @author Giuseppe Grosso
 *
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
	Optional<UserAccount> findByName(String name);
	Optional<UserAccount> findBySso(String sso);
	
	Optional<UserAccount> findByAzienda(String azienda2);
	
}