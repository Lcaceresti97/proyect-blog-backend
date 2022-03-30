package baas.com.sistemablogspringbootapirest.exception;

import baas.com.sistemablogspringbootapirest.model.Publication;

/**
 * Publication 404 status exception.
 * @autor Laurent Cáceres
 */
public class PublicationNotFoundException extends ResourceNotFoundException {

    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildPublicationNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(Publication.class, field, fieldValue);
    }

    /**
     *
     * @param publicationId Long
     * @return
     */
    public static ResourceNotFoundException
    buildPublicationNotFoundExceptionForId(String publicationId){
        return resourceNotFoundExceptionOf(Publication.class, "publicationId", publicationId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildPublicationNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(Publication.class, searchParams);
    }

}
