package it.plansoft.rubrica;/* ggrosso created on 30/01/2021 inside the package - it.plansoft.rubrica */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@PropertySource(ignoreResourceNotFound = true, value = {
		"file:///${env.application.conf.dir}/application.ext.properties" })
// altre configurazioni.
public class RubricaExtEnvCnfApplication {
	public static void main(String[] args) {
		SpringApplication.run(RubricaExtEnvCnfApplication.class, args);
	}
}
