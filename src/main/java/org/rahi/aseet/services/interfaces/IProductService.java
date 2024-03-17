package org.rahi.aseet.services.interfaces;

import org.rahi.aseet.Entities.ProductEntity;
import org.rahi.aseet.Entities.ProductImagesEntity;
import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.payload.request.AddProductRequest;
import org.rahi.aseet.payload.request.UploadImageRequest;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    public List<ProductImagesEntity> addImage(UploadImageRequest uploadImageRequest, UUID productId);
    public ProductEntity saveProduct(AddProductRequest addProductRequest, UserAccountEntity user);


}
