package az.core.service;

import az.core.model.dto.request.UserRequestDto;
import az.core.model.dto.response.UserResponseDto;
import az.core.model.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();

    UserResponseDto getById(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    UserResponseDto deleteUser(Long id);

    UserResponseDto addUser(UserRequestDto userRequestDto);
}
