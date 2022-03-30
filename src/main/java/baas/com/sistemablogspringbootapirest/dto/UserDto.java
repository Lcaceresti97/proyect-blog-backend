package baas.com.sistemablogspringbootapirest.dto;

import baas.com.sistemablogspringbootapirest.model.Rol;
import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Comment DTO class to encapsulate implementation of User entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Builder
@Getter
@Setter
@JsonSerialize
public class UserDto {


    @JsonProperty("user_id")
    private String userId;

    @JsonProperty(required = true)
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 150)
    private String username;

    @JsonProperty(required = true)
    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 64)
    @Email(message = "User email must be valid")
    private String email;

    @JsonProperty(required = true)
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 32)
    private String userPassword;

    @JsonProperty("userStatus")
    private ModelStatus userStatus;

    @JsonProperty("userRols")
    @Builder.Default
    private List<Rol> userRols = new ArrayList<>();
}
