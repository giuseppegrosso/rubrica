package it.plansoft.rubrica.controller;

import it.plansoft.rubrica.model.Rubrica;
import it.plansoft.rubrica.service.RubricaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/rubrica")
public class RubricaController extends BaseController<RubricaService, Rubrica, Long> {


    @Autowired
    public RubricaController(RubricaService service) {
        super(service);
    }

    @Override
    protected String getIndexPage() {
        return "rubrica";
    }


}