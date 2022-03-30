package baas.com.sistemablogspringbootapirest.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResponseDTO {

    private String accessToken;

    private String tokenType = "Bearer";;


    public JWTAuthResponseDTO(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public JWTAuthResponseDTO(String accessToken, String tokenType) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

}
