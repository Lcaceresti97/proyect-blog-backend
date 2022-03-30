package baas.com.sistemablogspringbootapirest.repository;

import baas.com.sistemablogspringbootapirest.model.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Publication entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Repository
public interface PublicationRepository extends JpaRepository<Publication, String> {


    /**
     * Find Publication by its title.
     * @param publicationTitle String
     * @return Optional Publication
     */
    Optional<Publication> findByPublicationTitle(String publicationTitle);


    /**
     * Find Paginated publication by title
     * @param publicationTitle
     * @param pageable
     */
    Page<Publication> findByPublicationTitleContaining(String publicationTitle, Pageable pageable);
}
