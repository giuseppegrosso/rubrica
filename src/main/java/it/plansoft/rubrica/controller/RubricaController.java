package it.plansoft.rubrica.controller;

import it.plansoft.rubrica.model.Rubrica;
import it.plansoft.rubrica.service.RubricaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rubrica")
public class RubricaController extends BaseCrudController<RubricaService, Rubrica, Long> {


    @Autowired
    public RubricaController(RubricaService service) {
        super(service);
    }

}