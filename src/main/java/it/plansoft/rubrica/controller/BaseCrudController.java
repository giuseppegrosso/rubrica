package it.plansoft.rubrica.controller;

import it.plansoft.rubrica.model.IDModel;
import it.plansoft.rubrica.service.BaseCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class BaseCrudController<SERVICE extends  BaseCrudService, MODEL extends IDModel<ID>, ID> implements ICrudController<MODEL, ID> {

    protected final static Logger log = LoggerFactory.getLogger(BaseCrudController.class);

    protected SERVICE service;

    public BaseCrudController(SERVICE service)
    {
        this.service = service;
    }

    @GetMapping("/")
    public List<MODEL> getAllItems() {
        log.info("getAllItems");
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIM', 'ROLE_USER')")
    public Optional<MODEL> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public MODEL add(@RequestBody MODEL model) {
        return (MODEL) service.save(model);
    }

    @PutMapping("/{id}")
    public MODEL update(@RequestBody MODEL model, @PathVariable ID id) {

        return (MODEL) service.getById(id).map(Item -> {
            model.setId(id);
            return service.save(model);
        }).orElseGet(() -> {
            return service.save(model);
        });
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        service.deleteById(id);
    }


    @Override
    @DeleteMapping("/")
    public void deleteItem(MODEL item) {
        service.delete(item);
    }
}