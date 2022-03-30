package baas.com.sistemablogspringbootapirest.dto;

import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Comment DTO class to encapsulate implementation of Rol entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class RolDto {

    @JsonProperty("rol_id")
    private String rolId;


    @JsonProperty(required = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String rolName;

    @JsonProperty("rol_status")
    private ModelStatus rolStatus;
}
