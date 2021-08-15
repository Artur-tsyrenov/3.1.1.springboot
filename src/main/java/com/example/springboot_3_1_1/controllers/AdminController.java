package com.example.springboot_3_1_1.controllers;

import com.example.springboot_3_1_1.models.Role;
import com.example.springboot_3_1_1.models.User;
import com.example.springboot_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("listUsers", userService.findAll());
        model.addAttribute("roles", userService.findAllRoles());
        return "admin/all";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/show";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findById(id));
        return "admin/editor";
    }

    @PostMapping
    public String createUser(
            @ModelAttribute(value = "roleAdmin") String roleAdmin,
            @ModelAttribute("user") User user) {

        Set<Role> setRole = new HashSet<>();
        if (roleAdmin.contains("on")) {
            setRole.add(userService.findAllRoles().get(1));
            setRole.add(userService.findAllRoles().get(0));
        } else {
            setRole.add(userService.findAllRoles().get(0));
        }
        user.setRoles(setRole);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/create")
    public String newUser(Model model) {
        String roleAdmin = null;
        model.addAttribute("user", new User());
        model.addAttribute("roleAdmin", roleAdmin);
        return "admin/editor";
    }
}
