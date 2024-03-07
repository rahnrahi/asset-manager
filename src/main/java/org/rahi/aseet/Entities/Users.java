package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity(name = "AssetUser")
@Data
@EqualsAndHashCode(callSuper=false, of = "userId")
public class Users extends BaseEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String token;


    @JsonProperty("full_name")
    @NotBlank(message = "Name is mandatory")
    private String name;


    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String passwordSalt;


}
