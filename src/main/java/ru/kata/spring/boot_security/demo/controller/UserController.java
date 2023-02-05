package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping
    public String showUserById(Principal principal, Model model) {
        model.addAttribute("something", principal.getName() + " table");
        model.addAttribute("user", userServiceImp.findByUsername(principal.getName()));
        return "oneuser";
    }
}

