package az.core.service.impl;

import az.core.config.MailConfiguration;
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

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public User addUser(User user) {
        mailConfiguration.sendMail(user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id ,User user) {
        User existUser = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setSurname(user.getSurname());
        existUser.setOrganization(user.getOrganization());
        existUser.setNumber(user.getNumber());
        existUser.setPosition(user.getPosition());
        existUser.setCourse(user.getCourse());
        return existUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
