package az.core.mapper;

import az.core.model.dto.UserDto;
import az.core.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    List<UserDto> entitiesToDto(List<User> users);

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);
}
