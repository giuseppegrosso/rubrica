package it.plansoft.rubrica.controller;/* ggrosso created on 21/01/2021 inside the package - it.plansoft.rubrica.controller */

import it.plansoft.rubrica.model.Rubrica;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rubricavis")
public class RubricaVisController {

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
    
    @GetMapping(path = "/{id}")
    public Rubrica getById(@PathVariable Long id) {
        return RUBRICA.get(0);
    }
}
