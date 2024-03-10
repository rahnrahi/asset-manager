package org.rahi.aseet.payload.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.rahi.aseet.Entities.AssetNode;

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
