package it.plansoft.rubrica.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.rubrica.model.Rubrica;
import it.plansoft.rubrica.service.RubricaService;

@RestController
@RequestMapping("/rubrica")

public class RubricaController {

    private final static Logger log = LoggerFactory.getLogger(RubricaController.class);

    @Autowired
    private RubricaService rubricaService;

    @GetMapping("/")
    public List<Rubrica> getAll() {

        return rubricaService.getAll();
    }

    @GetMapping("/{idRubrica}")
    public Optional<Rubrica> getById(@PathVariable Long idRubrica) {
        return rubricaService.getById(idRubrica);
    }

    @PostMapping("/")
    // @RequestMapping() // alternativa
    public Rubrica add(@RequestBody Rubrica rubrica) {
        return rubricaService.save(rubrica);
    }

    @PutMapping("/{id}")
    public Rubrica replaceEmployee(@RequestBody Rubrica newRubrica, @PathVariable Long id) {

        return rubricaService.getById(id).map(Rubrica -> {
            Rubrica.setCognome(newRubrica.getCognome());
            Rubrica.setNome(newRubrica.getNome());
            Rubrica.setIndirizzo(newRubrica.getIndirizzo());

            return rubricaService.save(Rubrica);
        }).orElseGet(() -> {
            newRubrica.setId(id);
            return rubricaService.save(newRubrica);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteRubrica(@PathVariable Long id) {
        rubricaService.deleteById(id);
    }

}