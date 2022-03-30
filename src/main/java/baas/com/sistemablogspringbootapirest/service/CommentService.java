package baas.com.sistemablogspringbootapirest.service;

import baas.com.sistemablogspringbootapirest.dto.CommentDto;
import baas.com.sistemablogspringbootapirest.exception.CommentNotFoundException;
import org.springframework.data.domain.Page;

/**
 * Service interface for Comment entity crud operations.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
public interface CommentService {

    /**
     * Find a Comment by its ID.
     * @param commentId String
     * @return Comment CommentDto
     * @throws CommentNotFoundException when no Comment is found by ID
     */
    CommentDto findCommentById(final String publication_id, final String commentId) throws CommentNotFoundException;

    /**
     * Find a Comment by its name.
     * @param commentName String
     * @return Comment CommentDto
     * @throws CommentNotFoundException when no Comment is found by name
     */
    CommentDto findByCommentName(final String commentName) throws CommentNotFoundException;

    /**
     * Return a page of sorted Comment.
     * @param commentName Comment title to sort by.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Comment.
     */
    Page<CommentDto> findPaginatedSortedComments(String commentName, int page, int size, String[] sort);

    /**
     * Saves given Comment into DB.
     * @param commentDto Comment
     * @param publication_id publicationId
     */
    CommentDto saveComment(String publication_id, CommentDto commentDto);


    /**
     * Delete Comment by its ID.
     * @param commentId
     */
    void deleteCommentById(final String publication_id,final String commentId);

}
