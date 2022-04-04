package baas.com.sistemablogspringbootapirest.service;

import baas.com.sistemablogspringbootapirest.dto.PublicationDto;
import baas.com.sistemablogspringbootapirest.dto.RolDto;

/**
 * Service interface for Publication entity crud operations.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
public interface RolService {

    /**
     * Saves given Rol into DB.
     * @param rolDto Rol
     */
    RolDto saveRol(RolDto rolDto);
}
