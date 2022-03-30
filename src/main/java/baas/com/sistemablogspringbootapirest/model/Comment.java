package baas.com.sistemablogspringbootapirest.model;

import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Comment class to represent Comment entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @Column(name = "comment_id", unique = true, nullable = false, length = 64)
    private String commentId;

    @Column(name = "comment_name", nullable = false)
    private String commentName;

    @Column(name = "comment_email", nullable = false)
    private String commentEmail;

    @Column(name = "comment_body", nullable = false)
    private String commentBody;

    @Column(name = "comment_status")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus commentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @JsonIgnore
    public Publication getPublication() {
        return publication;
    }

    @JsonIgnore
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    /**
     * Adds fields which are not populated by Customer DTO.
     * @return
     */
    public static Comment buildFromDtoComment(Comment comment){
        comment.setCommentIdId(UUID.randomUUID().toString());
        comment.setCommentStatus(ModelStatus.ACTIVE);
        return comment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.commentName == null ? 0 : this.commentName.hashCode());
        hash = 31 * hash + (this.commentBody == null ? 0 : this.commentBody.hashCode());
        return hash;
    }


    private void setCommentIdId(final String commentId) { this.commentId = commentId;}

    public void setCommentStatus(ModelStatus modelStatus) { this.commentStatus = modelStatus;}
}
