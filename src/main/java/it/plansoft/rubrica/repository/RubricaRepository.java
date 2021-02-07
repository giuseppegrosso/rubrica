package it.plansoft.rubrica.repository;

import it.plansoft.rubrica.model.Rubrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubricaRepository extends JpaRepository<Rubrica, Long> {

    List<Rubrica> findByCognome(String cognome);

}