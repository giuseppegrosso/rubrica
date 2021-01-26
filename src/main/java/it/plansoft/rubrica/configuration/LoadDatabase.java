package it.plansoft.rubrica.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.plansoft.rubrica.model.Rubrica;
import it.plansoft.rubrica.model.UserAccount;
import it.plansoft.rubrica.repository.RubricaRepository;
import it.plansoft.rubrica.repository.UserAccountRepository;
import it.plansoft.rubrica.repository.UserRepository;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	public CommandLineRunner LoadDatabaseTest(final RubricaRepository rubrica, UserRepository urepo,
			UserAccountRepository uaccount) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {

				// caricamento utenti/account/ruoli
				// ..
				log.info("insert userAccount {} ", uaccount.save(new UserAccount("Grosso", "Giuseppe",
						"giuseppe.ing.grosso@gmail.com", "Microsoft", "giuseppe", "giuseppe", "ADMIN|READ|WRITE")));
				log.info("insert userAccount {} ", uaccount.save(new UserAccount("Grosso", "Lorenzo",
						"giuseppe.ing.grosso@gmail.com", "Google", "lorenzo", "lorenzo", "USER|READ")));
				log.info("insert userAccount {} ", uaccount.save(new UserAccount("Grosso", "Daniele",
						"giuseppe.ing.grosso@gmail.com", "Linkedin", "daniele", "daniele", "VISUALIZZATORE|READ")));
				
				log.info("insert userAccount {} ", uaccount.save(new UserAccount("Grosso", "xxxxxx",
						"xxx@gmail.com", "Google", "xxx", "xxx", "USER|READ")));
				

				// rubrica
				log.info("insert rubrica {} ", rubrica.save(new Rubrica("Grosso", "Giuseppe", "via delle panche 13")));
				log.info("insert rubrica {} ", rubrica.save(new Rubrica("Grosso", "Daniele", "via delle panche 13")));
				log.info("insert rubrica {} ", rubrica.save(new Rubrica("Grosso", "Lorenzo", "via delle panche 13")));

			}
		};

//		notazione java 8	
//        return args -> {
//			log.info("insert rubrica {} ", rubrica.save(new Rubrica("Grosso", "Giuseppe", "via delle panche 13")));
//			
//        };
	}
}