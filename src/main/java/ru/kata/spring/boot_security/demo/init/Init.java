package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class Init {
    private final UserService userService;

    public Init(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {
        User admin = new User("admin", "admin", "admin@mail.ru", Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        User user = new User("user", "user", "user@mail.ru", Collections.singleton(new Role(1L, "ROLE_USER")));
        userService.saveUser(admin);
        userService.saveUser(user);
    }
}
