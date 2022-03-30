package baas.com.sistemablogspringbootapirest.dto.openapi;

import baas.com.sistemablogspringbootapirest.dto.RolDto;
import baas.com.sistemablogspringbootapirest.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used to define @Schema for RolDto response wrapper in openapi documentation.
 */
@Schema(name = "ResponseRolDto")
public class ResponseRolDto extends BaseResponse<RolDto> {
}
