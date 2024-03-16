package org.rahi.aseet.controllers;


import jakarta.validation.Valid;
import org.rahi.aseet.Entities.ProductEntity;
import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.payload.request.AddProductRequest;
import org.rahi.aseet.payload.request.AssetRequest;
import org.rahi.aseet.payload.response.AssetResponse;
import org.rahi.aseet.repositories.UserRepository;
import org.rahi.aseet.services.ProductService;
import org.rahi.aseet.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("add-new")
    public ProductEntity addAsset(@Valid @ModelAttribute AddProductRequest addProductRequest) throws Exception {
        UserAccountEntity user = getUser();

        return productService.saveProduct(addProductRequest, user);
    }

    private UserAccountEntity getUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserAccountEntity> user =  userRepository.findByEmail(authentication.getName());
        if(user.isEmpty()){
            throw new Exception("User not found");
        }
        return user.get();
    }

}
