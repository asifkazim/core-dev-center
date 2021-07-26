package az.core.service.impl;

import az.core.config.MailConfiguration;
import az.core.mapper.UserMapper;
import az.core.model.dto.UserDto;
import az.core.model.entity.User;
import az.core.repository.UserRepository;
import az.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final MailConfiguration mailConfiguration;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.entitiesToDto(users);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        mailConfiguration.sendMail(userDto);
        User user = userMapper.dtoToEntity(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (user != null) {
            user = userMapper.dtoToEntity(userDto);
            user.setId(id);
        }
        userRepository.save(user);
        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
