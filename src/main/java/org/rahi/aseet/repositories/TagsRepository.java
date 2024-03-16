package org.rahi.aseet.repositories;

import org.rahi.aseet.Entities.AssetNodeEntity;
import org.rahi.aseet.Entities.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagsRepository extends JpaRepository<TagsEntity, UUID> {
    @Query(value = "select t from Tags t where t.tagName= :name")
    Optional<TagsEntity> findByName(String name);
}
