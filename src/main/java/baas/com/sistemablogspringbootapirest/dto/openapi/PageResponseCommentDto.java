package baas.com.sistemablogspringbootapirest.dto.openapi;

import baas.com.sistemablogspringbootapirest.dto.CommentDto;
import baas.com.sistemablogspringbootapirest.dto.pageable.PageResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PageResponseCommentDto")
public class PageResponseCommentDto extends PageResponseDto<CommentDto> {
}
