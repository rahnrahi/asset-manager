package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity(name = "ProductBrand")
@Data
@EqualsAndHashCode(callSuper=false, of = "brandId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BrandEntity extends BaseEntity{
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID brandId;

    @NotNull
    private String brandName;

    @Column(length = 500)
    private String summery;

    @Column(length = 2000)
    private String content;
}
