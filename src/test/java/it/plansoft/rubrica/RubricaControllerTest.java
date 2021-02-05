package it.plansoft.rubrica;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import it.plansoft.rubrica.controller.RubricaController;
import it.plansoft.rubrica.model.Rubrica;

/*
 * test sugli endpoint della rubrica
 */
@SpringBootTest
public class RubricaControllerTest extends BaseCrudControllerTest<RubricaController, Rubrica, Long> {
	
	
    @Autowired
    public RubricaControllerTest(RubricaController controller) {
        super(controller);
    }

	@Test
	@WithMockUser(username = "giuseppe", password = "giuseppe")
	public void getRubricaList() throws Exception {
		super.getAllItems();
		
		// test specifici su oggetto.
	}

	@Test
	@WithMockUser(username = "giuseppe", password = "giuseppe")
	public void getById() throws Exception {
		super.getById(1L);
	}
}