package it.plansoft.rubrica;/* ggrosso created on 07/02/2021 inside the package - it.plansoft.rubrica */

import it.plansoft.rubrica.model.UserAccount;
import it.plansoft.rubrica.repository.UserAccountRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * test repository standalone
 */
public class RepositoryTest extends AbstractRepoTest {


    @Autowired
    private UserAccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testStandAloneReposirtory() throws Exception {
        Optional<UserAccount> user = this.repository.findBySso("giuseppe");
        assertEquals(user.get().getName(), "Giuseppe");
        assertEquals(user.get().getAzienda(), "Microsoft");
    }

    @Override
    protected void loadDataBase() {
        this.repository.save(new UserAccount("Grosso", "Giuseppe",
                "giuseppe.ing.grosso@gmail.com", "Microsoft", "giuseppe", passwordEncoder.encode("giuseppe"), "ADMIN|READ|WRITE"));

    }
}
