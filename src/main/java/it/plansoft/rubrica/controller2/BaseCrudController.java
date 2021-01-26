package it.plansoft.rubrica.controller2;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.plansoft.rubrica.model.BaseModel;
import it.plansoft.rubrica.service.BaseCrudService;

/**
 * classe di implementazione di base per le operazioni CRUD
 * 
 * @author Giuseppe Grosso
 * @since 26/01/2021
 */
public abstract class BaseCrudController<SERVICE extends BaseCrudService, MODEL extends BaseModel<ID>, ID>
		implements ICrudController<MODEL, ID> {

	protected final static Logger log = LoggerFactory.getLogger(BaseCrudController.class);

	public BaseCrudController(SERVICE service) {
		this.service = service;
	}

	/**
	 * oggetto di business che effettua la logica
	 */
	protected SERVICE service;

	@Override
	@GetMapping("/")
	public List<MODEL> getAll() {
		log.info("getAll");
		return service.getAll();
	}

	@Override
	@GetMapping("/{id}")
	public Optional<MODEL> getById(@PathVariable ID id) {
		return service.getById(id);
	}

	@Override
	@PutMapping("/{id}")
	public MODEL update(@RequestBody MODEL model, @PathVariable ID id) {
		log.info("update", model, id);
		return (MODEL) service.update(model);
	}

	@Override
	@PutMapping("/")
	public MODEL add(@RequestBody MODEL model) {
		log.info("add", model);
		return (MODEL) service.save(model);
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable ID id) {
		log.info("deleteById", id);
		service.deleteById(id);
	}

	@Override
	@DeleteMapping("/")
	public void delete(MODEL model) {
		log.info("delete", model);
		service.delete(model);
	}

}
