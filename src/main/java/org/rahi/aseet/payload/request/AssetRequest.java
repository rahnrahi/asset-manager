package org.rahi.aseet.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AssetRequest {
    @NotBlank
    private String name;

    private UUID parentId=null;
}
