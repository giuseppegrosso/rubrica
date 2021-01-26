package it.plansoft.rubrica.controller2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.rubrica.model.Rubrica;
import it.plansoft.rubrica.service.RubricaService;

@RestController
@RequestMapping("/rubrica2")
public class Rubrica2Controller extends BaseCrudController<RubricaService, Rubrica, Long> {

	private final static Logger log = LoggerFactory.getLogger(Rubrica2Controller.class);

	@Autowired
	public Rubrica2Controller(RubricaService service) {
		super(service);
	}


}
