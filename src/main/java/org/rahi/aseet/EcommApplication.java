package org.rahi.aseet;

import org.rahi.aseet.Entities.seeds.SeedProductCategories;
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

    @Autowired
    private SeedProductCategories seedProductCategories;

    public static void main(String[] args) {
        SpringApplication.run(EcommApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            seedUsers.generateAccounts();
            seedProductCategories.addAssets();
        };
    }

}
