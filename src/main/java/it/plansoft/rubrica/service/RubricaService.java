package it.plansoft.rubrica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.plansoft.rubrica.model.Rubrica;
import it.plansoft.rubrica.repository.RubricaRepository;

/**
 * classe di gestione della rubrica
 */
@Service
public class RubricaService extends BaseCrudService<RubricaRepository, Rubrica, Long> {

	@Autowired
	public RubricaService(RubricaRepository rubricaRepository) {
		super(rubricaRepository);
	}

}