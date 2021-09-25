package az.core.mapper;

import az.core.model.dto.request.UserRequestDto;
import az.core.model.dto.response.UserResponseDto;
import az.core.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    List<UserResponseDto> entitiesToDto(List<User> users);

    UserResponseDto entityToDto(User user);

    User dtoToEntity(UserRequestDto userRequestDto);
}
