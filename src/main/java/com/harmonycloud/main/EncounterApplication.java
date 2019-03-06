package com.harmonycloud.main;

import org.apache.servicecomb.saga.omega.spring.EnableOmega;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableOmega
@ComponentScan("com.harmonycloud")
@EnableJpaRepositories("com.harmonycloud.repository")
@EntityScan("com.harmonycloud.entity")
@EnableSwagger2
public class EncounterApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncounterApplication.class, args);
    }

}

