package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteUserById(long id);

    User getUserById(long id);
}
