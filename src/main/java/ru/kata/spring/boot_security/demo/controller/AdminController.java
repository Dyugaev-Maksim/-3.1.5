package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {
    public UserRepository userRepository;
    UserService userService;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsersList(Model model) {
        model.addAttribute("something", "All User table");
        model.addAttribute("user", userRepository.findAll());
        return "user";
    }

    @GetMapping("/{username}")
    public String showUserById(@PathVariable("username") String username, Model model) {
        model.addAttribute("something", "One User table");
        model.addAttribute("user", userRepository.findByUsername(username));
        return "one user";
    }

    @GetMapping("/new")
    public String getSavingPage(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String saveNewUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String getPageToUpd(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userRepository.getById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        userRepository.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }
}

