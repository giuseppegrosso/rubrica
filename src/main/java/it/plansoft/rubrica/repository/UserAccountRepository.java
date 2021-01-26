package it.plansoft.rubrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.plansoft.rubrica.model.UserAccount;

/**
 * repo per utenti account ruoli
 * @author Giuseppe Grosso
 *
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
    
}