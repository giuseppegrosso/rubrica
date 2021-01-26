package it.plansoft.rubrica.controller2;

import java.util.List;
import java.util.Optional;

/**
 * Controller per le operazioni CRUD
 * 
 * @author Giuseppe Grosso
 * @since 26/01/2021
 */
public interface ICrudController<MODEL, ID> extends IController {

	/**
	 * recupero tutti gli oggetti (non paginato)
	 * 
	 * @return
	 */
	public List<MODEL> getAll();

	/**
	 * recupero un oggetto specifico
	 */
	public Optional<MODEL> getById(ID id);

	/**
	 * aggiornamento dell'entity
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	public MODEL update(MODEL model, ID id);

	/**
	 * inserimento dei un nuovo entity
	 * 
	 * @param model
	 * @return
	 */
	public MODEL add(MODEL model);

	/**
	 * cancellazione oggetto tramite il suo ID
	 * 
	 * @param id
	 */
	public void deleteById(ID id);

	/**
	 * cancellazione dell'oggetto
	 * 
	 * @param model
	 */
	public void delete(MODEL model);

}
