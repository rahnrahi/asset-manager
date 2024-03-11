package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Users")
@Data
@EqualsAndHashCode(callSuper=false, of = "userId")
public class UserAccountEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    @JsonProperty("full_name")
    @NotBlank(message = "Name is mandatory")
    private String name;


    @NotBlank(message = "Email is mandatory")
    @Column(unique=true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<RoleEntity> roles = new HashSet<>();


}
