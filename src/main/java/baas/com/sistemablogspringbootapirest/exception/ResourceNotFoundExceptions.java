package baas.com.sistemablogspringbootapirest.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@Setter
public class ResourceNotFoundExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundExceptions(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with : %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
