package baas.com.sistemablogspringbootapirest.model;

import baas.com.sistemablogspringbootapirest.model.status.ModelStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "email" }) })
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 64)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    /**
    @Column(name = "user_status")
    @Enumerated(EnumType.ORDINAL)
    private ModelStatus userStatus;
    */

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_rols", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> userRols = new ArrayList<>();

}
