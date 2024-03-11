package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity(name = "AssetNode")
@Data
@EqualsAndHashCode(callSuper=false, of = "assetId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssetNodeEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID assetId;

    @NotNull
    @Column(unique=true)
    private String name;

    private int level = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private AssetNodeEntity parentNode;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parentNode")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private List<AssetNodeEntity> children;

    @JsonIgnore
    public List<AssetNodeEntity> getChildren() {
        return children.stream().toList();
    }

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "assetNodes")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<ProductEntity> products;

}
