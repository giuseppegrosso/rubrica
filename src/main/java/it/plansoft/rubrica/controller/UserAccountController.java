package it.plansoft.rubrica.controller;


import it.plansoft.rubrica.model.UserAccount;
import it.plansoft.rubrica.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userAccount")
public class UserAccountController extends BaseCrudController<UserAccountService, UserAccount, Long> {

    @Autowired
    public UserAccountController(UserAccountService service) {
        super(service);
    }

    @GetMapping("/azienda/{azienda}")
    public List<UserAccount> getUserAccountByAzienda(@PathVariable String azienda) {
        log.info("getUserAccountByAzienda", azienda);
        return ((UserAccountService) service).IsUserFromFactory(azienda);
    }

    @GetMapping("/ruoli/{sso_id}")
    public String getRoles(@PathVariable String sso_id) {
        log.info("getRoles", sso_id);
        return ((UserAccountService) service).getRoles(sso_id);
    }


    @PutMapping("/disable/{id}")
    public UserAccount update(@RequestBody UserAccount model, @PathVariable Long id) {

        return (UserAccount) service.getById(id).map(Item -> {
            model.setId(id);
            model.setExpired(true);
            return service.save(model);
        }).orElseThrow(() -> new RuntimeException("user non trovato"));
    }

}