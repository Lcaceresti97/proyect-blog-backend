package baas.com.sistemablogspringbootapirest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;


/**
 * Login DTO class to encapsulate implementation of Login entity
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@Getter
@Setter
public class LoginDto {

    private String usernameOrEmail;

    private String password;
}
