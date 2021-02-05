package it.plansoft.rubrica;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import it.plansoft.rubrica.controller.ICrudController;

public class BaseCrudControllerTest<CONTROLLER extends ICrudController<MODEL, ID>, MODEL, ID>
		implements ICrudController<MODEL, ID> {

	public BaseCrudControllerTest() {

	}

	public BaseCrudControllerTest(CONTROLLER controller) {
		this.controller = controller;
	}

	CONTROLLER controller;

	@Override
	public List<MODEL> getAllItems() {
		List<MODEL> rlist = controller.getAllItems();

		assertTrue(rlist.size() > 0);

		return rlist;
	}

	@Override
	public Optional<MODEL> getById(ID id) {
		Optional<MODEL> r = controller.getById(id);

		assertNotNull(r);
		// controllare che id sia = a id passato
		
		
		return r;
	}

	@Override
	public MODEL add(MODEL model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MODEL update(MODEL model, ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(ID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteItem(MODEL model) {
		// TODO Auto-generated method stub

	}

}
