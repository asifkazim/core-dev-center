package az.core.service.impl;

import az.core.config.MailConfiguration;
import az.core.error.EntityNotFoundException;
import az.core.mapper.UserMapper;
import az.core.model.dto.request.UserRequestDto;
import az.core.model.dto.response.UserResponseDto;
import az.core.model.entity.User;
import az.core.repository.UserRepository;
import az.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final MailConfiguration mailConfiguration;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.info("getAllUser requesting...");
        List<User> user = userRepository.findAll();
        log.info("getAllUser Response started with: {}", kv("user", user));
        return userMapper.entitiesToDto(user);
    }

    @Override
    public UserResponseDto getById(Long id) {
        log.info("getById User started with: {}", kv("id", id));
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(User.class, id);
        });
        UserResponseDto responseDto = userMapper.entityToDto(user);
        log.info("getById User completed successfully with: {}", kv("id", id));
        return responseDto;

    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        log.info("create User started with:{}", userRequestDto);
        mailConfiguration.sendMail(userRequestDto);
        User user = userMapper.dtoToEntity(userRequestDto);
        userRepository.save(user);
        UserResponseDto userResponseDto = userMapper.entityToDto(user);
        log.info("create User completed successfully with: {}", kv("user", userResponseDto));
        return userResponseDto;
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        log.info("update User started with: {}, {}", kv("id", id),
                kv("UserRequestDto", userRequestDto));
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(User.class, id);
        });
        if (user != null) {
            user = userMapper.dtoToEntity(userRequestDto);
            user.setId(id);
        }
        userRepository.save(user);
        UserResponseDto responseDto = userMapper.entityToDto(user);
        log.info("update User completed successfully with: {}, {}", kv("id", id),
                kv("user", responseDto));
        return responseDto;
    }

    @Override
    public UserResponseDto deleteUser(Long id) {
        log.info("Delete User started with: {}", kv("id", id));
        User user = userRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(User.class, id);
                }
        );
        userRepository.delete(user);
        UserResponseDto responseDto = userMapper.entityToDto(user);
        log.info("delete User completed successfully with: {}", kv("id", id));
        return responseDto;
    }

}



