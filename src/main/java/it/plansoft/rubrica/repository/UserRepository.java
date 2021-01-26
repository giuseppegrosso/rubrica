package it.plansoft.rubrica.repository;

import it.plansoft.rubrica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * classe repo per gli utenti
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}