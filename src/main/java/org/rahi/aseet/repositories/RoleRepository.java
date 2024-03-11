package org.rahi.aseet.repositories;

import org.rahi.aseet.Entities.RoleEntity;
import org.rahi.aseet.Entities.types.RoleTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(RoleTypes name);
}
