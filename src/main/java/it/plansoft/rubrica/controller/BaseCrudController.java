package it.plansoft.rubrica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.plansoft.rubrica.model.IDModel;
import it.plansoft.rubrica.service.BaseCrudService;

/**
 * controller per i metodi crud.
 * @param <SERVICE>
 * @param <MODEL>
 * @param <ID>
 */
public class BaseCrudController<SERVICE extends BaseCrudService, MODEL extends IDModel<ID>, ID>
        extends AbstractController<SERVICE>
        implements ICrudController<MODEL, ID> {


    public BaseCrudController(SERVICE service) {
        this.service = service;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            //log user name
            log.info(authentication.getName());
        }
    }


    @GetMapping("/")
    public List<MODEL> getAllItems() {
        log.info("getAllItems");
        return service.getAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIM', 'ROLE_USER')")
    public Optional<MODEL> getById(@PathVariable ID id) {
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