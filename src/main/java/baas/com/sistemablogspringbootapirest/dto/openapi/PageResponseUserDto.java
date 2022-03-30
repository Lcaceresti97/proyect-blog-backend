package baas.com.sistemablogspringbootapirest.dto.openapi;

import baas.com.sistemablogspringbootapirest.dto.UserDto;
import baas.com.sistemablogspringbootapirest.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponsePublicationDto")
public class PageResponseUserDto extends PageResponseDto<UserDto> {
}
