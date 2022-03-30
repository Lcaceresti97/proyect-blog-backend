package baas.com.sistemablogspringbootapirest.dto.openapi;

import baas.com.sistemablogspringbootapirest.dto.CommentDto;
import baas.com.sistemablogspringbootapirest.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class used to define @Schema for CommentDto response wrapper in openapi documentation.
 */
@Schema(name = "ResponseCommentDto")
public class ResponseCommentDto extends BaseResponse<CommentDto> {
}
