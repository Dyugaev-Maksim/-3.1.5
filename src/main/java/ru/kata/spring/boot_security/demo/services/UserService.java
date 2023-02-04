package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    List<User> getAllUsers();

    void deleteUserById(long id);

    User getUserById(long id);
}
