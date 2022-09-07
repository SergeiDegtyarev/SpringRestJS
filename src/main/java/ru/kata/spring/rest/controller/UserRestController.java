package ru.kata.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.rest.model.User;
import ru.kata.spring.rest.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;

    @Autowired
    protected UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getAuthorizedUser(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        return userService.getUserByUsername(user.getUsername());
    }
}
