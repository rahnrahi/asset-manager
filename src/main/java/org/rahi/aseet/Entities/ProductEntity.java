package org.rahi.aseet.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Product")
@Data
@EqualsAndHashCode(callSuper=false, of = "productId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductEntity extends BaseEntity{
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID productId;

    @NotNull
    private String productTitle;

    @NotNull
    @Column(length = 500)
    private String summery;

    @NotNull
    @Column(length = 2000)
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Set<AssetNodeEntity> assetNodes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Set<TagsEntity> tagsEntityList;
}
