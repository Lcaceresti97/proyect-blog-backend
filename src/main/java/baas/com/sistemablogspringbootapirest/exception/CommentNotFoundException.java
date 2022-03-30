package baas.com.sistemablogspringbootapirest.exception;

import baas.com.sistemablogspringbootapirest.model.Publication;

/**
 * Comment 404 status exception.
 * @autor Laurent Cáceres
 */
public class CommentNotFoundException extends ResourceNotFoundException{


    /**
     *
     * @param field
     * @param fieldValue
     * @return
     */
    public static ResourceNotFoundException
    buildCommentNotFoundExceptionForField(String field, String fieldValue){
        return resourceNotFoundExceptionOf(Publication.class, field, fieldValue);
    }

    /**
     *
     * @param commentId Long
     * @return
     */
    public static ResourceNotFoundException
    buildCommentNotFoundExceptionForId(String commentId){
        return resourceNotFoundExceptionOf(Publication.class, "commentId", commentId);
    }

    /**
     *
     * @param searchParams
     * @return ResourceNotFoundException instance
     */
    public static ResourceNotFoundException buildCommentNotFoundException(String... searchParams){
        return resourceNotFoundExceptionOf(Publication.class, searchParams);
    }

}
