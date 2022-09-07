package ru.kata.spring.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class PageController {

    @GetMapping("/admin")
    public String goToAdmin() {
        return "admin";
    }

    @GetMapping("/user")
    public String gotToUser() {
        return "user";
    }
}