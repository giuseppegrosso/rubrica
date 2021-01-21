package it.plansoft.rubrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.rubrica.model.Rubrica;

@Repository
public interface RubricaRepository extends JpaRepository<Rubrica, Long>{
    
}