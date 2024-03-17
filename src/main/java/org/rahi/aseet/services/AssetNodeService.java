package org.rahi.aseet.services;

import org.rahi.aseet.Entities.AssetNodeEntity;
import org.rahi.aseet.payload.request.AssetRequest;
import org.rahi.aseet.payload.response.AssetResponse;
import org.rahi.aseet.repositories.AssetNodeRepository;
import org.rahi.aseet.services.interfaces.IAssetNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class AssetNodeService implements IAssetNodeService {


    AssetNodeRepository _iassetNoderepo;

    @Autowired
    AssetNodeService(AssetNodeRepository iAssetNode){
        _iassetNoderepo = iAssetNode;
    }


    /**
     * @param parentId
     * @return List<AssetNode>
     */
    @Override
    public List<AssetResponse> getNodesbyParentId(UUID parentId) {

        List<AssetNodeEntity> items =  _iassetNoderepo.findNodesByParentId(parentId).stream().toList();
        List<AssetResponse> assetResponseList = new java.util.ArrayList<>(Collections.emptyList());
        for (AssetNodeEntity item : items){
            assetResponseList.add(new AssetResponse(
                    item.getName(),
                    item.getParentNode().getAssetId(),
                    item.getAssetId(),
                    item.getLevel()
                    )
            );
        }
        return assetResponseList;
    }

    /**
     * @return AssetNode
     */
    @Override
    public AssetResponse saveAsset(AssetRequest nodedata) throws Exception {
      AssetNodeEntity parentNode = getNodeById(nodedata.getParentId());
      AssetNodeEntity newAsset = new AssetNodeEntity();
        return getAssetResponse(nodedata, parentNode, newAsset);
    }

    /**
     * @return AssetNode
     */
    @Override
    public AssetResponse updateAsset(AssetRequest nodedata, UUID assetId) throws Exception {
        AssetNodeEntity parentNode = getNodeById(nodedata.getParentId());
        AssetNodeEntity newAsset = getNodeById(assetId);
        if(parentNode==null){
            parentNode = newAsset.getParentNode();
        }
        return getAssetResponse(nodedata, parentNode, newAsset);
    }

    private AssetResponse getAssetResponse(AssetRequest nodedata, AssetNodeEntity parentNode, AssetNodeEntity newAsset) {
        newAsset.setName(nodedata.getName());

        if(parentNode==null){
            newAsset.setLevel(0);
        }else{
            newAsset.setLevel(parentNode.getLevel()+1);
            newAsset.setParentNode(parentNode);
        }
        AssetNodeEntity assetNode =  _iassetNoderepo.save(newAsset);
        return  new AssetResponse(
                assetNode.getName(),
                assetNode.getParentNode().getAssetId(),
                assetNode.getAssetId(),
                assetNode.getLevel()
        );
    }

    private AssetNodeEntity getNodeById(UUID assetId) throws Exception {
        if(assetId==null){
            return null;
        }
        var nodeDetails = _iassetNoderepo.findById(assetId);
        if(nodeDetails.isEmpty()){
            throw new Exception("Asset not found");
        }
        return nodeDetails.get();
    }
}
