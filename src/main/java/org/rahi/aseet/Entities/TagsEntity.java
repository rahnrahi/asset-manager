package org.rahi.aseet.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Tags")
@Data
@EqualsAndHashCode(callSuper=false, of = "tagId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TagsEntity extends BaseEntity{
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID tagId;

    @NotNull
    @Column(unique=true)
    private String tagName;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "tagsEntityList")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<ProductEntity> products;

}
