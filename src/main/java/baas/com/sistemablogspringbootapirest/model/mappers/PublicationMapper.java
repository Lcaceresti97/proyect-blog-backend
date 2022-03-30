package baas.com.sistemablogspringbootapirest.model.mappers;

import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.model.Publication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicationMapper {


    Publication dtoToPublication(PublicationDto dto);

    PublicationDto publicationToDto(Publication publication);

    List<Publication> dtoToPublication(List<PublicationDto> dtos);

    List<PublicationDto> publicationToDto(List<Publication> publications);
}
