package az.core.service;

import az.core.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getById(Long id);

    User addUser(User user);

    User updateUser(Long id,User user);

    void deleteUser(Long id);
}
