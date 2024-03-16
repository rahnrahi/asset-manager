package org.rahi.aseet.services;

import org.rahi.aseet.Entities.AssetNodeEntity;
import org.rahi.aseet.Entities.ProductEntity;
import org.rahi.aseet.Entities.TagsEntity;
import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.payload.request.AddProductRequest;
import org.rahi.aseet.repositories.AssetNodeRepository;
import org.rahi.aseet.repositories.ProductRepository;
import org.rahi.aseet.repositories.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AssetNodeRepository assetNodeRepository;

    @Autowired
    private TagsRepository tagsRepository;

    public ProductEntity saveProduct(AddProductRequest addProductRequest, UserAccountEntity user){
        try {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductTitle(addProductRequest.getTitle());
            productEntity.setSummery(addProductRequest.getSummery());
            if(addProductRequest.getContent()!=null){
                productEntity.setContent(addProductRequest.getContent());
            }

            Set<AssetNodeEntity> assetNodeEntities = getAssetListByIds(addProductRequest.getCategories());
            if(!assetNodeEntities.isEmpty()){
                productEntity.setAssetNodes(assetNodeEntities);
            }

            productEntity.setAddedBy(user);

            Set<TagsEntity> tagsEntities = upsertTags(addProductRequest.getTags());
            productEntity.setTagsEntityList(tagsEntities);
            return productRepository.save(productEntity);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Set<AssetNodeEntity> getAssetListByIds(List<UUID> ids){
        Set<AssetNodeEntity> assetNodeEntities = new HashSet<>(Collections.emptySet());
        List<AssetNodeEntity> assetNodeEntityList  =  assetNodeRepository.findAllById(ids);
        assetNodeEntities.addAll(assetNodeEntityList);
        return assetNodeEntities;
    }

    private Set<TagsEntity> upsertTags(List<String> tags){
        Set<TagsEntity> tagsEntities = new HashSet<>(Collections.emptySet());
       for(String tag : tags){
           Optional<TagsEntity> tagobj  =  tagsRepository.findByName(tag);
           if(tagobj.isPresent()){
               tagsEntities.add(tagobj.get());
           }else{
               TagsEntity newtagEntity = new TagsEntity();
               newtagEntity.setTagName(tag);
               TagsEntity tagsEntity = tagsRepository.save(newtagEntity);
               tagsEntities.add(tagsEntity);
           }
       }
       return tagsEntities;
    }

}
