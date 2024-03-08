package org.rahi.aseet.controllers;


import jakarta.validation.Valid;
import org.rahi.aseet.Entities.AssetNode;
import org.rahi.aseet.payload.request.AssetRequest;
import org.rahi.aseet.services.AssetNodeService;
import org.rahi.aseet.services.IAssetNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asset")
public class AssetController {

    IAssetNodeService _assetNodeService;

    @Autowired
    AssetController(AssetNodeService assetNodeService){
      _assetNodeService = assetNodeService;
    }

    @PostMapping("save-asset")
    public AssetNode addAsset(@Valid @ModelAttribute AssetRequest assetRequest) throws Exception {
     return _assetNodeService.saveAsset(assetRequest);
    }

}
