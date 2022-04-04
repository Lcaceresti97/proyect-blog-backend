package baas.com.sistemablogspringbootapirest.service.implementation;

import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.exception.PublicationNotFoundException;
import baas.com.sistemablogspringbootapirest.model.Publication;
import baas.com.sistemablogspringbootapirest.model.mappers.PublicationMapper;
import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import baas.com.sistemablogspringbootapirest.repository.PublicationRepository;
import baas.com.sistemablogspringbootapirest.service.PublicationService;
import baas.com.sistemablogspringbootapirest.utils.Messages;
import baas.com.sistemablogspringbootapirest.utils.SortingPagingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Publication entity.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    private final PublicationMapper publicationMapper;

    private final SortingPagingUtils sortingPagingUtils;

    private final Messages messages;


    @Override
    public PublicationDto findPublicationById(final String publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> PublicationNotFoundException.buildPublicationNotFoundExceptionForId(publicationId));
        return publicationMapper.publicationToDto(isActivePublication(publication, "publicationId", publicationId));
    }

    @Override
    public PublicationDto findByPublicationTitle(final String publicationTitle) {
        Publication publication = publicationRepository.findByPublicationTitle(publicationTitle)
                .orElseThrow(() -> PublicationNotFoundException.buildPublicationNotFoundExceptionForField("publicationTitle", publicationTitle));
        return publicationMapper.publicationToDto(isActivePublication(publication, "publicationTitle", publicationTitle));
    }

    @Override
    public Page<PublicationDto> findPaginatedSortedPublications(String publicationTitle, int page, int size, String[] sort) {
        List<Sort.Order> orders = sortingPagingUtils.getSortOrders(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<PublicationDto> publicationDtos;
        if(publicationTitle == null) {
            publicationDtos = publicationMapper.publicationToDto(publicationRepository.findAll(pageable).toList());
        } else {
            publicationDtos = publicationMapper.publicationToDto(publicationRepository.findByPublicationTitleContaining(publicationTitle, pageable).toList());
        }
        return new PageImpl<>(publicationDtos);
    }

    @Override
    public ArrayList<PublicationDto> findPaginatedSortedAllPublications(final int pageNo, final int pageSize, final String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        List<PublicationDto> publicationDtos;
        publicationDtos = publicationMapper
                .publicationToDto(publicationRepository.findAll(paging).toList());

        return new ArrayList<>(publicationDtos);
    }

    @Override
    public PublicationDto savePublication(PublicationDto publicationDto) {
        Publication publication = Publication.buildFromDtoPublication(this.publicationMapper.dtoToPublication(publicationDto));
        this.publicationRepository.save(publication);
        return publicationMapper.publicationToDto(publication);
    }

    @Override
    public void deletePublicationById(final String publicationId) {

        Publication publication = publicationMapper.dtoToPublication(findPublicationById(publicationId));
        publication.setPublicationStatus(ModelStatus.INACTIVE);
        publicationRepository.save(publication);
    }

    /**
     * Return publication if status code is ACTIVE.
     * @param publication Publication
     * @param queryField String
     * @param queryFieldValue String
     * @return Publication
     * @throws PublicationNotFoundException ex
     */
    private Publication isActivePublication(Publication publication, String queryField, String queryFieldValue){
        if(publication.getPublicationStatus().getStatusCode() == 0){
            return publication;
        }
        throw PublicationNotFoundException
                .buildPublicationNotFoundExceptionForField(queryField, queryFieldValue);
    }
}
