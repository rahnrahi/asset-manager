package org.rahi.aseet.controllers;


import jakarta.validation.Valid;
import org.rahi.aseet.payload.request.AssetRequest;
import org.rahi.aseet.payload.response.AssetResponse;
import org.rahi.aseet.services.AssetNodeService;
import org.rahi.aseet.services.IAssetNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/asset")
public class AssetController {

    IAssetNodeService _assetNodeService;

    @Autowired
    AssetController(AssetNodeService assetNodeService){
      _assetNodeService = assetNodeService;
    }

    @PostMapping("save-asset")
    public AssetResponse addAsset(@Valid @ModelAttribute AssetRequest assetRequest) throws Exception {
     return _assetNodeService.saveAsset(assetRequest);
    }

    @GetMapping("get-asset/{assetId}")
    public List<AssetResponse> getNode(@PathVariable UUID assetId) throws Exception {
        return _assetNodeService.getNodesbyParentId(assetId);
    }
    @PutMapping("update-asset/{assetId}")
    public AssetResponse addAsset(@Valid @ModelAttribute AssetRequest assetRequest, @PathVariable UUID assetId) throws Exception {
        return _assetNodeService.updateAsset(assetRequest, assetId);
    }

}
