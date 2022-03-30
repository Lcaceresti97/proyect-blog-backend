package baas.com.sistemablogspringbootapirest.dto.openapi;

import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used to define @Schema for PublicationDto response wrapper in openapi documentation.
 */
@Schema(name = "ResponsePublicationDto")
public class ResponsePublicationDto extends BaseResponse<PublicationDto> {
}
