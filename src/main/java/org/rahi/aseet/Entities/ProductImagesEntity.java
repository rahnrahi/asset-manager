package org.rahi.aseet.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity(name = "ProductImages")
@Data
@EqualsAndHashCode(callSuper=false, of = "productImageId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductImagesEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID productImageId;

    private String imageName;

    private String extension;

    private long size;

    @ManyToOne
    private ProductEntity product;


}
