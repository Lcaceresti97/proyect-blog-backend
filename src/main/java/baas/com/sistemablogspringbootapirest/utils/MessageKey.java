package baas.com.sistemablogspringbootapirest.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageKey     {

    //Not found.
    PUBLICATION_NOT_FOUND("publication-not-found");

    public final String key;

    public String getKey(){
        return this.key;
    }
}
