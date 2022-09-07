package ru.kata.spring.rest.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.rest.model.User;

import java.util.List;


public interface UserService extends UserDetailsService {

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User findUserById(long id);

    User getUserByUsername(String username);
}
