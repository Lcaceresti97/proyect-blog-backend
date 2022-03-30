package baas.com.sistemablogspringbootapirest.dto.openapi;

import baas.com.sistemablogspringbootapirest.dto.RolDto;
import baas.com.sistemablogspringbootapirest.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponseRolDto")
public class PageResponseRolDto extends PageResponseDto<RolDto> {
}
