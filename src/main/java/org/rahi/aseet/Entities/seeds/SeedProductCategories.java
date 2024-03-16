package org.rahi.aseet.Entities.seeds;

import com.github.javafaker.Faker;
import org.rahi.aseet.Entities.AssetNodeEntity;
import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.repositories.AssetNodeRepository;
import org.rahi.aseet.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class SeedProductCategories {

    @Autowired
    private AssetNodeRepository assetNodeRepository;
    private final Faker faker;

    SeedProductCategories(){
        faker = new Faker(new Locale("en-US"));
    }

    public void addAssets(){
       long count = assetNodeRepository.findAll().size();
        if (count >0) {
            return;
        }
        List<AssetNodeEntity> assetNodeEntityArrayList = new java.util.ArrayList<>(Collections.emptyList());
        for (int i = 0; i < 10; i++) {
            AssetNodeEntity assetNodeEntity = new AssetNodeEntity();
            assetNodeEntity.setName(faker.lorem().sentence(8));
            assetNodeEntityArrayList.add(assetNodeEntity);
        }
        assetNodeRepository.saveAll(assetNodeEntityArrayList);
    }


}
