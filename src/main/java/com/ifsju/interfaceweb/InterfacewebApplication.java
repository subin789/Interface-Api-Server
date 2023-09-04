package com.ifsju.interfaceweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InterfacewebApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterfacewebApplication.class, args);
    }

}
