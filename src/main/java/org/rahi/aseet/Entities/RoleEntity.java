package org.rahi.aseet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.rahi.aseet.Entities.types.RoleTypes;


@Entity(name = "UserRoles")
@Data
@EqualsAndHashCode(callSuper=false, of = "roleId")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    @NotNull
    private RoleTypes name;
}
