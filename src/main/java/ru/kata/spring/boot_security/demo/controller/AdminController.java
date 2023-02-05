package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserService(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping
    public String getAllUsersList(Model model) {
        model.addAttribute("something", "All User table");
        model.addAttribute("user", userServiceImp.getAllUsers());
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("something", "One User table");
        model.addAttribute("user", userServiceImp.getUserById(id));
        return "adminoneuser";
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
        userServiceImp.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String getPageToUpd(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userServiceImp.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }
        userServiceImp.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImp.deleteUserById(id);
        return "redirect:/admin";
    }
}

