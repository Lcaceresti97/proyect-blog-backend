package baas.com.sistemablogspringbootapirest.controller;

import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.dto.openapi.PageResponsePublicationDto;
import baas.com.sistemablogspringbootapirest.dto.openapi.ResponsePublicationDto;
import baas.com.sistemablogspringbootapirest.dto.pageable.PageResponse;
import baas.com.sistemablogspringbootapirest.dto.pageable.PageResponseDto;
import baas.com.sistemablogspringbootapirest.response.BaseResponse;
import baas.com.sistemablogspringbootapirest.response.Response;
import baas.com.sistemablogspringbootapirest.response.error.ErrorResponse;
import baas.com.sistemablogspringbootapirest.service.PublicationService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for Publication entity operations.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/publications")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;

    /**
     * Handler method for fetching a single Publication by its ID.
     * @param publicationId String
     * @return ResponseEntity Publication
     */
    @Operation(description = "Find a Publication by its ID.", operationId = "findPublicationById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication retrieved successfully"
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  PublicationDto.class))})
            , @ApiResponse(responseCode = "404", description = "Publication not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(value = "/{publicationId}")
    public ResponseEntity<? extends PublicationDto> findByPublicationId(@PathVariable final String publicationId) {
        PublicationDto retrievedPublication = publicationService.findPublicationById(publicationId);
        return new ResponseEntity<>(retrievedPublication, HttpStatus.OK);
    }

    /**
     * Handler method for fetching a single Publication by its title.
     * @param publicationTitle String
     * @return ResponseEntity Publication
     */
    @Operation(description = "Find a Publication by its title.", operationId = "findPublicationByTitle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication retrieved successfully"
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  PublicationDto.class))})
            , @ApiResponse(responseCode = "404", description = "Publication not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(params = "publicationTitle")
    public ResponseEntity<? extends  PublicationDto> findPublicationByTitle(@RequestParam("publicationTitle") final String publicationTitle){
        PublicationDto retrievedPublication = publicationService.findByPublicationTitle(publicationTitle);
        return new ResponseEntity<>(retrievedPublication, HttpStatus.OK);
    }

    /**
     * Get Paginated sorted publications with given criteria.
     * @param publicationTitle String publicationTitle
     * @param page Page number
     * @param size page size
     * @param sort Sort params
     * @return ResponseEntity PageResponse PublicationDto
     */
    @Operation(summary = "Get a list of paginated/sorted publications", operationId = "getPublications")
    @ApiResponse(responseCode = "200", description = "List of publications retrieved successfully."
            ,  content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
            , schema = @Schema(implementation = PageResponsePublicationDto.class))})
    @GetMapping(params = {"publicationTitle", "page", "size", "sort"})
    public ResponseEntity<? extends PageResponse<PublicationDto>> getPublications(
            @RequestParam(required = false)String publicationTitle,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "publicationId, desc") String[] sort) {

        Page<PublicationDto> publicationPage = publicationService
                .findPaginatedSortedPublications(publicationTitle, page, size, sort);

        PageResponseDto<PublicationDto> pageResponseDto = new PageResponseDto<>();
        return pageResponseDto.buildResponseEntity(publicationPage.getSize(), publicationPage.getNumberOfElements(),
                publicationPage.getTotalPages(), publicationPage.getNumber(), publicationPage.getContent());
    }

    /**
     * Handler method for saving Validated given Publication.
     * @param publicationDto PublicationDto
     * @return ResponseEntity Response Publication
     */
    @Operation(summary = "Save given Publication.", operationId = "savePublication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",  description = "Publication saved successfully"
                    ,content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation = ResponsePublicationDto.class))})
            , @ApiResponse(responseCode = "400", description = "Given Publication is invalid"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<? extends Response<PublicationDto>> savePublication(@RequestBody @Valid PublicationDto publicationDto) {
        PublicationDto savedPublication = publicationService.savePublication(publicationDto);
        BaseResponse<PublicationDto> publicationBaseResponse = new BaseResponse<>();
        return publicationBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Publication saved successfully", savedPublication);
    }

    /**
     * Handler method for deleting a Publication by its ID.
     * @param publicationId String
     * @return Response null
     */
    @Operation(description = "Delete a Publication by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication deleted successfully."
                    , content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                    , schema = @Schema(implementation =  String.class))})
            , @ApiResponse(responseCode = "404", description = "Publication not found"
            , content = { @Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{publicationId}")
    public ResponseEntity<? extends Response<String>> deletePublication(@PathVariable final String publicationId) {
        publicationService.deletePublicationById(publicationId);
        BaseResponse<String> publicationResponse = new BaseResponse<>();
        return publicationResponse
                .buildResponseEntity(HttpStatus.OK, new StringBuilder("Publication with ID: ")
                        .append(publicationId)
                        .append(" was deleted.").toString(), publicationId);
    }
}
