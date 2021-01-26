package it.plansoft.rubrica;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.plansoft.rubrica.controller.RubricaController;
import it.plansoft.rubrica.model.Rubrica;


@SpringBootTest
public class RubricaControllerTest  {
	@Autowired
	RubricaController controller;

    @Test
    public void getRubricaList() throws Exception {
       List<Rubrica> rlist = controller.getAllItems();

       assertTrue(rlist.size() > 0);
    }

    @Test
    public void getById() throws Exception {
       Optional<Rubrica> r = controller.getById(1L);

       assertNotNull(r);
    }
}