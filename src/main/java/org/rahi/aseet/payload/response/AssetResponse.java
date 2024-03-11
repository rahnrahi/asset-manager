package org.rahi.aseet.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AssetResponse {

    private String name;

    private UUID parentId;

    private UUID asseId;

    private int level=0;

}
