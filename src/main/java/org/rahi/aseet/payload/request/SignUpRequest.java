package org.rahi.aseet.payload.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    @Size(min = 1)
    @Valid
    private List<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
