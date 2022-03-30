package baas.com.sistemablogspringbootapirest.model.mappers;


import baas.com.sistemablogspringbootapirest.dto.UserDto;
import baas.com.sistemablogspringbootapirest.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToUser(UserDto dto);

    UserDto userToDto(User user);

    List<User> dtoToUser(List<UserDto> dtos);

    List<UserDto> userToDto(List<User> users);
}
