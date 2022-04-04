package baas.com.sistemablogspringbootapirest.service;

import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.exception.PublicationNotFoundException;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

/**
 * Service interface for Publication entity crud operations.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
public interface PublicationService {

    /**
     * Find a Publication by its ID.
     * @param publicationId String
     * @return Publication PublicationDto
     * @throws PublicationNotFoundException when no Publication is found by ID
     */
    PublicationDto findPublicationById(final String publicationId) throws PublicationNotFoundException;


    /**
     * Find a publication by its title.
     * @param publicationTitle String
     * @return publication PublicationDto
     * @throws PublicationNotFoundException when no Publication is found by name
     */
    PublicationDto findByPublicationTitle(final String publicationTitle) throws PublicationNotFoundException;

    /**
     * Return a page of sorted Publication.
     * @param publicationTitle Publication title to sort by.
     * @param page Page number to query by.
     * @param size Page size to query by.
     * @param sort Extra sort params to sort by.
     * @return PageResponseDto Publication.
     */
    Page<PublicationDto> findPaginatedSortedPublications(String publicationTitle, int page, int size, String[] sort);


    /**
     * Return a page of sorted all publication.
     * @param pageNo Page number to query by.
     * @param pageSize Page size to query by.
     * @param sortBy Extra sort params to sort by.
     * @return PageResponseDto Publication.
     */
    ArrayList<PublicationDto> findPaginatedSortedAllPublications(int pageNo, int pageSize, String sortBy);

    /**
     * Saves given Publication into DB.
     * @param publicationDto Publication
     */
    PublicationDto savePublication(PublicationDto publicationDto);


    /**
     * Delete Publication by its ID.
     * @param publicationId
     */
    void deletePublicationById(final String publicationId);



}
