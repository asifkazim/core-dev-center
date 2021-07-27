package az.core.service.impl;

import az.core.config.MailConfiguration;
import az.core.error.UserNotFoundException;
import az.core.mapper.UserMapper;
import az.core.model.dto.UserDto;
import az.core.model.entity.User;
import az.core.repository.UserRepository;
import az.core.service.UserService;
import az.core.util.ApplicationCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final MailConfiguration mailConfiguration;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Request, GET All Users");
        List<User> users = userRepository.findAll();
        log.info("Response, users:{}", users);
        return userMapper.entitiesToDto(users);
    }

    @Override
    public UserDto getById(Long id) {
        log.info("Request, GET User By Id, id:{}", id);
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ApplicationCode.USER_NOT_FOUND, "User not found!")
        );
        log.info("Response, User By Id, id:{}, user:{} ", id, user);
        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        log.info("Request, ADD User, user:{}", userDto);
        log.info("Sending mail to admin...");
        mailConfiguration.sendMail(userDto);
        User user = userMapper.dtoToEntity(userDto);
        userRepository.save(user);
        log.info("Response, ADDED User, user:{}", user);
        return userDto;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        log.info("Request, UPDATE User, id:{}, user:{}", id, userDto);
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ApplicationCode.USER_NOT_FOUND, "User not found!")
        );

        if (user != null) {
            user = userMapper.dtoToEntity(userDto);
            user.setId(id);
        }
        userRepository.save(user);
        log.info("Request, UPDATE User, id:{}, user:{}", id, user);
        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Request, DELETE User, id:{}", id);
        userRepository.deleteById(id);
        log.info("Response, DELETED User");
    }
}
