package baas.com.sistemablogspringbootapirest.dto.openapi;

import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponsePublicationDto")
public class PageResponsePublicationDto extends PageResponseDto<PublicationDto> {
}
