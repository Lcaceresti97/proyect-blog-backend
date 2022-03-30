package baas.com.sistemablogspringbootapirest.dto.openapi;

import baas.com.sistemablogspringbootapirest.dto.UserDto;
import baas.com.sistemablogspringbootapirest.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used to define @Schema for UserDto response wrapper in openapi documentation.
 */
@Schema(name = "ResponseUserDto")
public class ResponseUserDto extends BaseResponse<UserDto> {
}
