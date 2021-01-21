package it.plansoft.rubrica.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.plansoft.rubrica.model.Rubrica;
import it.plansoft.rubrica.repository.RubricaRepository;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	public CommandLineRunner LoadDatabaseTest(final RubricaRepository rubrica) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
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