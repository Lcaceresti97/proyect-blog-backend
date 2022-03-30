package baas.com.sistemablogspringbootapirest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class BlogAppException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;


}
