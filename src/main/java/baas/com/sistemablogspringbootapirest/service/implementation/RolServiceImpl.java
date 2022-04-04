package baas.com.sistemablogspringbootapirest.service.implementation;

import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.dto.RolDto;
import baas.com.sistemablogspringbootapirest.model.Publication;
import baas.com.sistemablogspringbootapirest.model.Rol;
import baas.com.sistemablogspringbootapirest.model.mappers.RolMapper;
import baas.com.sistemablogspringbootapirest.repository.RolRepository;
import baas.com.sistemablogspringbootapirest.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for Rol entity.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    private final RolMapper rolMapper;


    @Override
    public RolDto saveRol(RolDto rolDto) {
        Rol rol = Rol.buildFromDtoRol(this.rolMapper.dtoToRol(rolDto));
        this.rolRepository.save(rol);
        return rolMapper.rolToDto(rol);
    }
}
