package it.plansoft.rubrica;/* ggrosso created on 30/01/2021 inside the package - it.plansoft.rubrica */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration // prendo la properties in --spring.config.location=file:/C:/Users/ggrosso/GiuseppeGrossoFolder/download/temp/
public class RubricaExtCnfApplication {
    public static void main(String[] args) {
        SpringApplication.run(RubricaExtCnfApplication.class, args);
    }
}
