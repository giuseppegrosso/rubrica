package it.plansoft.rubrica.controller;/* ggrosso created on 30/01/2021 inside the package - it.plansoft.rubrica.repository */

import it.plansoft.rubrica.model.Rubrica;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/produce",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        method = {RequestMethod.GET, RequestMethod.POST})
public class ProducesConsumesController {

    private static final List<Rubrica> RUBRICA = Arrays.asList(
            new Rubrica("Grosso", "Giuseppevis", "via non lo so"),
            new Rubrica("Grosso", "Lorenzovis", "via non lo so"),
            new Rubrica("Grosso", "Danielevis", "via non lo so")
    );


    @GetMapping(path = "/")
    public List<Rubrica> getDemo()
    {
        return RUBRICA;
    }

    @RequestMapping(path = "/xml", method = RequestMethod.GET)
    public List<Rubrica> getDemoXml()
    {
        return RUBRICA;
    }
}
