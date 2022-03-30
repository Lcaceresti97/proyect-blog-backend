package baas.com.sistemablogspringbootapirest.repository;

import baas.com.sistemablogspringbootapirest.model.Comment;
import baas.com.sistemablogspringbootapirest.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Comment entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    /**
     * Find Comment by its name.
     * @param commentName String
     * @return Optional Comment
     */
    Optional<Comment> findByCommentName(String commentName);


    /**
     * Find Paginated Comment by name
     * @param commentName
     * @param pageable
     */
    Page<Comment> findByCommentNameContaining(String commentName, Pageable pageable);

}
