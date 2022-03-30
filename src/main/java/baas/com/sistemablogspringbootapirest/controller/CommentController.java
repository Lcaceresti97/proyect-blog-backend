package baas.com.sistemablogspringbootapirest.controller;

import baas.com.sistemablogspringbootapirest.dto.CommentDto;
import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.dto.openapi.PageResponseCommentDto;
import baas.com.sistemablogspringbootapirest.dto.openapi.PageResponsePublicationDto;
import baas.com.sistemablogspringbootapirest.dto.openapi.ResponsePublicationDto;
import baas.com.sistemablogspringbootapirest.dto.pageable.PageResponse;
import baas.com.sistemablogspringbootapirest.dto.pageable.PageResponseDto;
import baas.com.sistemablogspringbootapirest.response.BaseResponse;
import baas.com.sistemablogspringbootapirest.response.Response;
import baas.com.sistemablogspringbootapirest.response.error.ErrorResponse;
import baas.com.sistemablogspringbootapirest.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for Comment entity operations.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Handler method for fetching a single Comment by its ID.
     * @param commentId String
     * @return ResponseEntity Comment
     */
    @Operation(description = "Find a Comment by its ID.", operationId = "findCommentById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment retrieved successfully"
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  CommentDto.class))})
            , @ApiResponse(responseCode = "404", description = "Comment not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(value = "/publication/{publicationId}/comments/{commentId}")
    public ResponseEntity<? extends CommentDto> findByCommentId(@PathVariable(value = "publicationId") String publicationId, @PathVariable final String commentId) {
        CommentDto retrievedComment = commentService.findCommentById(publicationId,commentId);
        return new ResponseEntity<>(retrievedComment, HttpStatus.OK);
    }

    /**
     * Handler method for fetching a single Comment by its name.
     * @param commentName String
     * @return ResponseEntity Comment

    @Operation(description = "Find a Comment by its name.", operationId = "findCommentByName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment retrieved successfully"
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  PublicationDto.class))})
            , @ApiResponse(responseCode = "404", description = "Comment not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(params = "commentName")
    public ResponseEntity<? extends  CommentDto> findCommentByName(@RequestParam("commentName") final String commentName){
        CommentDto retrievedComment = commentService.findByCommentName(commentName);
        return new ResponseEntity<>(retrievedComment, HttpStatus.OK);
    }
     */
    /**
     * Get Paginated sorted Comments with given criteria.
     * @param commentName String publicationTitle
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse CommentDto
     */
    @Operation(summary = "Get a list of paginated/sorted Comment", operationId = "getComments")
    @ApiResponse(responseCode = "200", description = "List of Comments retrieved successfully."
            ,  content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
            , schema = @Schema(implementation = PageResponseCommentDto.class))})
    @GetMapping(params = {"commentName", "page", "size", "sort"}, value = "/publication/comments")
    public ResponseEntity<? extends PageResponse<CommentDto>> getComments(
            @RequestParam(required = false)String commentName,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "commentId, desc") String[] sort) {

        Page<CommentDto> commentPage = commentService
                .findPaginatedSortedComments(commentName, page, size, sort);

        PageResponseDto<CommentDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(commentPage.getSize(), commentPage.getNumberOfElements(),
                commentPage.getTotalPages(), commentPage.getNumber(), commentPage.getContent());
    }

    /**
     * Handler method for saving Validated given Comment.
     * @param commentDto CommentDto
     * @return ResponseEntity Response Comment
     */
    @Operation(summary = "Save given Comment.", operationId = "saveComment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",  description = "Comment saved successfully"
                    ,content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation = PageResponseCommentDto.class))})
            , @ApiResponse(responseCode = "400", description = "Given Comment is invalid"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})}
    )
    @PostMapping("/publication/{publicationId}/comments")
    public ResponseEntity<? extends Response<CommentDto>> saveComment(@PathVariable(value = "publicationId") String publicationId, @RequestBody @Valid CommentDto commentDto) {
        CommentDto savedComment = commentService.saveComment(publicationId, commentDto);
        BaseResponse<CommentDto> commentBaseResponse = new BaseResponse<>();
        return commentBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Comment saved successfully", savedComment);
    }

    /**
     * Handler method for deleting a Comment by its ID.
     * @param commentId String
     * @return Response null
     */
    @Operation(description = "Delete a Comment by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted successfully."
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  String.class))})
            , @ApiResponse(responseCode = "404", description = "Comment not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping(value = "/publication/{publicationId}/comments/{commentId}")
    public ResponseEntity<? extends Response<String>> deleteComment(@PathVariable(value = "publicationId") final String publicationId, @PathVariable final String commentId) {
        commentService.deleteCommentById(publicationId, commentId);
        BaseResponse<String> commentResponse = new BaseResponse<>();
        return commentResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Comment with ID: ")
                        .append(commentId)
                        .append(" was deleted.").toString(), commentId);
    }

}
