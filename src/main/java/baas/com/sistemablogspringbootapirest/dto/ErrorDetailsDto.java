package baas.com.sistemablogspringbootapirest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class ErrorDetailsDto {

    @JsonProperty
    private Date timestamp;

    @JsonProperty
    private String message;

    @JsonProperty
    private String detailsMessage;


}
