package org.rahi.aseet.services.interfaces;

import org.rahi.aseet.payload.request.AssetRequest;
import org.rahi.aseet.payload.response.AssetResponse;

import java.util.List;
import java.util.UUID;

public interface IAssetNodeService {
    List<AssetResponse> getNodesbyParentId(UUID parentId);
    AssetResponse saveAsset(AssetRequest node) throws Exception;

    AssetResponse updateAsset(AssetRequest nodedata, UUID assetId) throws Exception;
}
