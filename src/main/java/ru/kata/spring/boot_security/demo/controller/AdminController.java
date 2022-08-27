package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    UserService userService;

    @Autowired
    protected AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(ModelMap model) {
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("users", usersList);
        return "admin";
    }

    @GetMapping("search")
    public String printUser(@RequestParam(value = "id", required = false, defaultValue = "0") long id,
                            ModelMap model) {
        model.addAttribute("users", List.of(userService.findUserById(id)));
        return "admin";
    }

    @GetMapping("new_user")
    public String newUserForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user, "ROLE_ADMIN"); // TODO take role from page
        return "redirect:/admin";
    }

    @GetMapping("edit/{id}")
    public String editUserForm(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.findUserById(id));
        return "edit_user";
    }

    @PostMapping("edit")
    public String updateUserInfo(@ModelAttribute("user") User user) {
        System.out.println(user);
        userService.updateUser(user, "ROLE_ADMIN"); // TODO take role from page
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
         userService.deleteUser(id);
         return "redirect:/admin";
    }
}