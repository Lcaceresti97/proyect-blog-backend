package baas.com.sistemablogspringbootapirest.model;

import javax.persistence.*;

import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.*;

/**
 * Publication class to represent Publication entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_publications")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Publication {

    @Id
    @Column(name = "publication_id", nullable = false, unique = true)
    private String publicationId;

    @Column(name = "publication_title", nullable = false)
    private String publicationTitle;

    @Column(name = "publication_description", nullable = false)
    private String publicationDescription;

    @Column(name = "publication_contents", nullable = false)
    private String publicationContents;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus publicationStatus;


    @JsonBackReference
    @OneToMany(mappedBy="publication", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> publicationComment = new ArrayList<>();

    /**
     * Adds fields which are not populated by Publication DTO.
     * @return
     */
    public static Publication buildFromDtoPublication(Publication publication){
        publication.setPublicationId(UUID.randomUUID().toString());
        publication.setPublicationStatus(ModelStatus.ACTIVE);
        return publication;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.publicationTitle == null ? 0 : this.publicationTitle.hashCode());
        hash = 31 * hash + (this.publicationContents == null ? 0 : this.publicationContents.hashCode());
        return hash;
    }


    private void setPublicationId(final String publicationId) { this.publicationId = publicationId;}

    public void setPublicationStatus(ModelStatus modelStatus) { this.publicationStatus = modelStatus;}
}
