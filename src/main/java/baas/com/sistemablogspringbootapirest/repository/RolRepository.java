package baas.com.sistemablogspringbootapirest.repository;

import baas.com.sistemablogspringbootapirest.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Rol entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, String> {

    /**
     * Find Rol by its name.
     * @param rolName String
     * @return Optional Rol
     */
    Optional<Rol> findByRolName(String rolName);

}
