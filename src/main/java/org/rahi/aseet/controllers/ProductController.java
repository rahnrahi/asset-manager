package org.rahi.aseet.controllers;


import jakarta.validation.Valid;
import org.rahi.aseet.Entities.ProductEntity;
import org.rahi.aseet.Entities.ProductImagesEntity;
import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.payload.request.AddProductRequest;
import org.rahi.aseet.payload.request.UploadImageRequest;
import org.rahi.aseet.repositories.UserRepository;
import org.rahi.aseet.services.ProductService;
import org.rahi.aseet.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    private final UserRepository userRepository;

    @Autowired
    ProductController(ProductService _productService, UserRepository _userRepository){
        productService = _productService;
        userRepository = _userRepository;
    }

    @PostMapping("add-new")
    public ProductEntity addAsset(@Valid @ModelAttribute AddProductRequest addProductRequest) throws Exception {
        UserAccountEntity user = getUser();

        return productService.saveProduct(addProductRequest, user);
    }

    @PostMapping("add-image/{productId}")
    public List<ProductImagesEntity> addImage(@Valid @ModelAttribute UploadImageRequest uploadImageRequest, @PathVariable UUID productId)
    {
        return productService.addImage(uploadImageRequest, productId);
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
