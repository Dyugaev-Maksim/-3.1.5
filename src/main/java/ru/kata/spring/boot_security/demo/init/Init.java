package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class Init {
    private final UserServiceImp userServiceImp;

    public Init(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostConstruct
    private void postConstruct() {
        User admin = new User("admin", "admin", "admin@mail.ru", Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        User user = new User("user", "user", "user@mail.ru", Collections.singleton(new Role(1L, "ROLE_USER")));
        userServiceImp.saveUser(admin);
        userServiceImp.saveUser(user);
    }
}
