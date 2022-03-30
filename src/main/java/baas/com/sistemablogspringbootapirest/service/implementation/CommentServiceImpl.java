package baas.com.sistemablogspringbootapirest.service.implementation;


import baas.com.sistemablogspringbootapirest.dto.CommentDto;
import baas.com.sistemablogspringbootapirest.exception.BlogAppException;
import baas.com.sistemablogspringbootapirest.exception.CommentNotFoundException;
import baas.com.sistemablogspringbootapirest.exception.ResourceNotFoundException;
import baas.com.sistemablogspringbootapirest.exception.ResourceNotFoundExceptions;
import baas.com.sistemablogspringbootapirest.model.Comment;
import baas.com.sistemablogspringbootapirest.model.Publication;
import baas.com.sistemablogspringbootapirest.model.mappers.CommentMapper;
import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import baas.com.sistemablogspringbootapirest.repository.CommentRepository;
import baas.com.sistemablogspringbootapirest.repository.PublicationRepository;
import baas.com.sistemablogspringbootapirest.service.CommentService;
import baas.com.sistemablogspringbootapirest.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Comment entity.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    public final CommentRepository commentRepository;

    public final PublicationRepository publicationRepository;

    public final CommentMapper commentMapper;

    private final SortingPagingUtils sortingPagingUtils;

    @Override
    public CommentDto findCommentById(final String publication_id,final String commentId) {

        Publication publication = publicationRepository.findById(publication_id).orElseThrow(() -> new ResourceNotFoundExceptions("Publication", "publication_id", publication_id));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.buildCommentNotFoundExceptionForId(commentId));

        if (!comment.getPublication().getPublicationId().equals(publication.getPublicationId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
        }

        return commentMapper.commentToDto(isActiveComment(comment, "commentId", commentId));
    }

    @Override
    public CommentDto findByCommentName(final String commentName) {
        Comment comment = commentRepository.findByCommentName(commentName)
                .orElseThrow(() -> CommentNotFoundException.buildCommentNotFoundExceptionForField("commentName", commentName));
        return commentMapper.commentToDto(isActiveComment(comment, "commentName", commentName));
    }

    @Override
    public Page<CommentDto> findPaginatedSortedComments(String commentName, int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<CommentDto> commentDtos;
        if(commentName == null) {
            commentDtos = commentMapper.commentToDto(commentRepository.findAll(pageable).toList());
        } else {
            commentDtos = commentMapper.commentToDto(commentRepository.findByCommentNameContaining(commentName, pageable).toList());
        }
        return new PageImpl<>(commentDtos);
    }

    @Override
    public CommentDto saveComment(String publication_id, CommentDto commentDto) {
        Comment comment = Comment.buildFromDtoComment(this.commentMapper.dtoToComment(commentDto));
        Publication publication = publicationRepository.findById(publication_id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Publication", "id", publication_id));
        comment.setPublication(publication);
        this.commentRepository.save(comment);
        return commentMapper.commentToDto(comment);
    }

    @Override
    public void deleteCommentById( String publication_id, String commentId) {

        Publication publication = publicationRepository.findById(publication_id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Publication", "id", publication_id));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Comment", "id", commentId));

        if(!comment.getPublication().getPublicationId().equals(publication.getPublicationId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
        }
        comment.setCommentStatus(ModelStatus.INACTIVE);
        commentRepository.save(comment);
    }

    /**
     * Return comment if status code is ACTIVE.
     * @param comment Comment
     * @param queryField String
     * @param queryFieldValue String
     * @return Comment
     * @throws CommentNotFoundException ex
     */
    private Comment isActiveComment(Comment comment, String queryField, String queryFieldValue){
        if(comment.getCommentStatus().getStatusCode() == 0){
            return comment;
        }
        throw CommentNotFoundException
                .buildCommentNotFoundExceptionForField(queryField, queryFieldValue);
    }
}
