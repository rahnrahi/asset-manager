package org.rahi.aseet;

import org.rahi.aseet.Entities.seeds.SeedUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommApplication {

    @Autowired
    private SeedUsers seedUsers;

    public static void main(String[] args) {
        SpringApplication.run(EcommApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            seedUsers.generateAccounts();
        };
    }

}
