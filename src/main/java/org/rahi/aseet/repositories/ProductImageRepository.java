package org.rahi.aseet.repositories;

import org.rahi.aseet.Entities.ProductImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductImageRepository extends JpaRepository<ProductImagesEntity, UUID> {
}
