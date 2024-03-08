package org.rahi.aseet.services;

import org.rahi.aseet.Entities.AssetNode;
import org.rahi.aseet.Entities.BaseEntity;
import org.rahi.aseet.payload.request.AssetRequest;
import org.rahi.aseet.repositories.IAssetNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssetNodeService implements IAssetNodeService{


    IAssetNode _iassetNoderepo;

    @Autowired
    AssetNodeService(IAssetNode iAssetNode){
        _iassetNoderepo = iAssetNode;
    }


    /**
     * @param parentId
     * @return List<AssetNode>
     */
    @Override
    public List<AssetNode> getNodesbyParentId(UUID parentId) {
        return _iassetNoderepo.findNodesByParentId(parentId);
    }

    /**
     * @return AssetNode
     */
    @Override
    public AssetNode saveAsset(AssetRequest nodedata) throws Exception {
      AssetNode parentNode = getNodeById(nodedata.getParentId());
      AssetNode newAsset = new AssetNode();
      newAsset.setName(nodedata.getName());

      if(parentNode==null){
          newAsset.setLevel(0);
      }else{
          newAsset.setLevel(parentNode.getLevel()+1);
          newAsset.setParentNode(parentNode);
      }
      return _iassetNoderepo.save(newAsset);
    }

    private AssetNode getNodeById(UUID assetId) throws Exception {
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
