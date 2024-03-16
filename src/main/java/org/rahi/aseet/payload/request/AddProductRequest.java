package org.rahi.aseet.payload.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AddProductRequest {

    @NotBlank
    @Size(min = 10, max = 250, message = "{validation.name.size}")
    private String title;
    @NotBlank
    @Size(min = 5, max = 500, message = "{validation.name.size}")
    private String summery;

    @Size(max = 1000, message = "{validation.name.size}")
    private String content;

    private List<String> tags;

    private List<UUID> categories;
}
