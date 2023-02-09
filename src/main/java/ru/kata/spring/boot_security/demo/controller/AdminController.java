package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userservice;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userservice, RoleService roleService) {
        this.userservice = userservice;
        this.roleService = roleService;
    }


    @GetMapping
    public String getAdminPanel(Model model, Principal principal) {
        User user = userservice.findByUsername(principal.getName());
        model.addAttribute("thisUser", user);
        model.addAttribute("users", userservice.getAllUsers());
        model.addAttribute("allRoles", roleService.getRolesSet());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/new")
    public String saveNewUser(@ModelAttribute("newUser") User newUser,
                              @RequestParam("roles") long[] roles) {
        newUser.setRoles(roleService.getRolesById(roles));
        userservice.saveUser(newUser);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("editUser") User user,
                             @RequestParam("roles") long[] selectedRoles) {
        user.setRoles(roleService.getRolesById(selectedRoles));
        userservice.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userservice.deleteUserById(id);
        return "redirect:/admin";
    }
}

