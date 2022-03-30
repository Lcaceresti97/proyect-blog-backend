package baas.com.sistemablogspringbootapirest.dto;

import baas.com.sistemablogspringbootapirest.model.Publication;
import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Comment DTO class to encapsulate implementation of Comment entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class CommentDto {

    @JsonProperty("comment_id")
    private String commentId;


    @JsonProperty(required = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String commentName;

    @JsonProperty(required = true)
    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 64)
    @Email(message = "Comment email must be valid")
    private String commentEmail;


    @JsonProperty(required = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String commentBody;

    @JsonProperty("comment_status")
    private ModelStatus commentStatus;

    @JsonIgnore
    @JsonProperty("publication")
    private Publication publication;
}
