package it.plansoft.rubrica;

import it.plansoft.rubrica.controller.RubricaController;
import it.plansoft.rubrica.model.Rubrica;
import it.plansoft.rubrica.repository.RubricaRepository;
import it.plansoft.rubrica.service.RubricaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

/*
 * test sugli endpoint della rubrica
 * check sul service
 * check sul repository
 *
 * Attenzione la gerarchia delle chiamate permette di effettuare i test sui Controller
 * sul service e sul repository per i soli metodi CRUD, inserire ulteriori test specifici
 * Es. per rubrica in repo getByCognome
 */
@SpringBootTest
public class RubricaControllerTest extends BaseCrudControllerTest<RubricaController, Rubrica, Long> {


    @Autowired
    public RubricaControllerTest(RubricaController controller, RubricaService service, RubricaRepository repo) {
        super(controller, service, repo);
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


    /**
     * repository getByCognome
     */
    @Test
    public void getByCognome() throws Exception {
        ((RubricaRepository)this.repo).findByCognome("Grosso");
    }


    @Override
    protected Rubrica getInsertElement() {
        return Rubrica
                .build()
                .setCognome("NewCognome")
                .setNome("NewNome")
                .setIndirizzo("NewIndirizzo")
                ;
    }
}