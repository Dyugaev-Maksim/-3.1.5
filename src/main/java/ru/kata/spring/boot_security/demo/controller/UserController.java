package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {
    public UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping
    public String showUserById(Principal principal, Model model) {
        model.addAttribute("something", principal.getName() + " table");
        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        return "oneuser(user)";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return "redirect:/login?logout";
    }
}
