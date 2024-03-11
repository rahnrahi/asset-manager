package org.rahi.aseet.repositories;

import org.rahi.aseet.Entities.AssetNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetNodeRepository extends JpaRepository<AssetNodeEntity, UUID> {
    @Query(value = "select n from AssetNode n left join n.parentNode p where p.assetId = :parentId")
    List<AssetNodeEntity> findNodesByParentId(UUID parentId);

}
