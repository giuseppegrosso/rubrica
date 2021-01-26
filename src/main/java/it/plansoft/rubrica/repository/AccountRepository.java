package it.plansoft.rubrica.repository;

import it.plansoft.rubrica.model.Account;
import it.plansoft.rubrica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * classe repo per i dati account
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    
}