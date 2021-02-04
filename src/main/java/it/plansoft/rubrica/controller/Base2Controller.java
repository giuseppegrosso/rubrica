package it.plansoft.rubrica.controller;/* ggrosso created on 04/02/2021 inside the package - it.plansoft.rubrica.controller */

import it.plansoft.rubrica.model.IDModel;
import it.plansoft.rubrica.service.BaseCrudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public abstract class Base2Controller<SERVICE extends BaseCrudService, MODEL extends IDModel<ID>, ID>
        extends  BaseCrudController<SERVICE, MODEL, ID>
{

    public Base2Controller(SERVICE service) {
        super(service);
    }

    protected abstract String getIndexPage();

    @GetMapping("")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getIndexPage());
        return modelAndView;
    }
}
