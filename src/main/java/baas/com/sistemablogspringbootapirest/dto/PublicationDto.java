package baas.com.sistemablogspringbootapirest.dto;

import baas.com.sistemablogspringbootapirest.model.Comment;
import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Publication DTO class to encapsulate implementation of Publication entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@JsonSerialize
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {

    @JsonProperty("publication_id")
    private String publicationId;

    @JsonProperty("publication_title")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 100)
    private String publicationTitle;

    @JsonProperty("publication_description")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 250)
    private String publicationDescription;

    @JsonProperty("publication_contents")
    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 250)
    private String publicationContents;

    @JsonProperty("status")
    private ModelStatus publicationStatus;

    @JsonProperty("publicationComment")
    @Builder.Default
    private List<Comment> publicationComment = new ArrayList<>();

}
