package baas.com.sistemablogspringbootapirest.model.mappers;

import baas.com.sistemablogspringbootapirest.dto.CommentDto;
import baas.com.sistemablogspringbootapirest.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment dtoToComment(CommentDto dto);

    CommentDto commentToDto(Comment comment);

    List<Comment> dtoToComment(List<CommentDto> dtos);

    List<CommentDto> commentToDto(List<Comment> comments);
}
