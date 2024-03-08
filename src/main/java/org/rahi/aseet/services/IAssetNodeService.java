package org.rahi.aseet.services;

import org.rahi.aseet.Entities.AssetNode;
import org.rahi.aseet.payload.request.AssetRequest;

import java.util.List;
import java.util.UUID;

public interface IAssetNodeService {
    List<AssetNode> getNodesbyParentId(UUID parentId);
    AssetNode saveAsset(AssetRequest node) throws Exception;
}
