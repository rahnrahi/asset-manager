package org.rahi.aseet.Entities.seeds;

import com.github.javafaker.Faker;
import org.rahi.aseet.Entities.RoleEntity;
import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.Entities.types.RoleTypes;
import org.rahi.aseet.repositories.RoleRepository;
import org.rahi.aseet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeedUsers {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;


    public void generateAccounts(){
        RoleEntity adminRole = roleRepository.findByName(RoleTypes.ROLE_ADMIN).orElse(null);
        if(adminRole!=null){
            return;
        }
        System.out.println("generating roles");
        List<RoleEntity> roleEntityList = new java.util.ArrayList<>(Collections.emptyList());
        for(RoleTypes role: RoleTypes.values()){
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(role);
            roleEntityList.add(roleEntity);
        }
        roleRepository.saveAll(roleEntityList);
        this.geneRateUsers();
    }

    private void geneRateUsers() {

        RoleEntity adminRole = roleRepository.findByName(RoleTypes.ROLE_ADMIN).orElse(null);
        RoleEntity customerRole = roleRepository.findByName(RoleTypes.ROLE_CUSTOMER).orElse(null);

        System.out.println("generating users");

        Faker faker = new Faker(new Locale("en-US"));
        List<UserAccountEntity> userAccountEntityList = new java.util.ArrayList<>(Collections.emptyList());

        UserAccountEntity userAccountEntity = new UserAccountEntity();
        Set<RoleEntity> administrates = new HashSet<>();
        administrates.add(adminRole);
        userAccountEntity.setRoles(administrates);
        userAccountEntity.setName("Rahi");
        userAccountEntity.setEmail("rahnrahi@gmail.com");
        userAccountEntity.setPassword(encoder.encode("rahi"));

        userAccountEntityList.add(userAccountEntity);

        Set<RoleEntity> customer = new HashSet<>();
        customer.add(customerRole);

        for (int i = 0; i < 10; i++) {
            UserAccountEntity customerEntity = new UserAccountEntity();
            customerEntity.setName(faker.name().name());
            customerEntity.setEmail(faker.internet().emailAddress());
            customerEntity.setPassword(encoder.encode("12345678"));
            customerEntity.setRoles(customer);
            userAccountEntityList.add(customerEntity);
        }

       userRepository.saveAll(userAccountEntityList);

    }




}
