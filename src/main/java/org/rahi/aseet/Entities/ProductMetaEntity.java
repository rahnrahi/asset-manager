package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity(name = "ProductMeta")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductMetaEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID productMetaId;

    @NotNull
    private String mataKey;

    @Column(length = 500)
    private String content;

    @ManyToOne
    private ProductEntity product;

}
