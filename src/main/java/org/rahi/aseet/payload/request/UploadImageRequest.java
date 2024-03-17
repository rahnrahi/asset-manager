package org.rahi.aseet.payload.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class UploadImageRequest {


    @Valid
    @NotNull
    @Size(min = 1, max = 5, message = "You can upload maximum 2 images.")
    private MultipartFile[] images;


}
