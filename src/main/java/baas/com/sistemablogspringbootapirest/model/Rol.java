package baas.com.sistemablogspringbootapirest.model;

import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import lombok.*;

import javax.persistence.*;

/**
 * Comment class to represent Rol entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Entity
@Table(name = "t_rol")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rol {

    @Id
    @Column(name = "rol_id", unique = true, nullable = false, length = 64)
        private String rolId;

    @Column(name = "rol_name", nullable = false)
    private String rolName;

    @Column(name = "rol_status")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus rolStatus;

}
