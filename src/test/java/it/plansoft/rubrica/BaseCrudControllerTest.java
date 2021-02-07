package it.plansoft.rubrica;

import it.plansoft.rubrica.controller.ICrudController;
import it.plansoft.rubrica.model.IDModel;
import it.plansoft.rubrica.service.BaseCrudService;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public abstract class BaseCrudControllerTest<CONTROLLER extends ICrudController<MODEL, ID>, MODEL extends IDModel<ID>, ID>
		implements ICrudController<MODEL, ID> {

	public BaseCrudControllerTest() {

	}

	CONTROLLER controller;
	BaseCrudService service;
	JpaRepository repo;

	public BaseCrudControllerTest(CONTROLLER controller,
								  BaseCrudService service,
								  JpaRepository repo) {
		this.controller = controller;
		this.service = service;
		this.repo = repo;
	}


	// abstract method
	protected abstract MODEL getInsertElement();

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


	/********************************************************************************
	 * TEST SERVICE
	 *********************************************************************************/

	@Test
	protected void getServiceById() throws Exception {
		List<MODEL> rlist = service.getAll();
		assertTrue(rlist.size() > 0);

		MODEL r = rlist.get(0);

		// get Data ById
		Optional<MODEL> e = service.getById(r.getId());

		assertNotNull(e);
		assertNotNull(e.get());

		// check di lettura id
		assertEquals(e.get().getId(), r.getId());
	}

	@Test
	protected void getServiceAll() throws Exception {

		List<MODEL> rlist = service.getAll();

		assertTrue(rlist.size() > 0);
	}

	@Test
	protected void insert() throws Exception {

		MODEL m = getInsertElement();

		MODEL mi = (MODEL)service.save(m);
		Optional<MODEL> e = service.getById(mi.getId());


		assertNotNull(e);
		assertNotNull(e.get());

		// check di lettura id
		assertEquals(e.get().getId(), mi.getId());
	}

	/********************************************************************************
	 * TEST REPOSITORY
	 *********************************************************************************/
	@Test
	protected void getRepoById() throws Exception {
		List<MODEL> rlist = repo.findAll();
		assertTrue(rlist.size() > 0);

		MODEL r = rlist.get(0);

		// get Data ById
		Optional<MODEL> e = repo.findById(r.getId());

		assertNotNull(e);
		assertNotNull(e.get());

		// check di lettura id
		assertEquals(e.get().getId(), r.getId());
	}
}
