package baas.com.sistemablogspringbootapirest.model.mappers;


import baas.com.sistemablogspringbootapirest.dto.RolDto;
import baas.com.sistemablogspringbootapirest.model.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol dtoToRol(RolDto dto);

    RolDto rolToDto(Rol rol);

    List<Rol> dtoToRol(List<RolDto> dtos);

    List<RolDto> rolToDto(List<Rol> rols);
}
