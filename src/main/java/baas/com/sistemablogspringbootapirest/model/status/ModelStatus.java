package baas.com.sistemablogspringbootapirest.model.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ModelStatus {

    ACTIVE(0),
    INACTIVE(1);

    private final int statusCode;
}
