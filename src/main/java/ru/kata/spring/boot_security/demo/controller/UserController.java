package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    protected UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String printInformation(ModelMap model, HttpServletRequest request) {
        // TODO take user info by username
        model.addAttribute("userObj", userService.loadUserByUsername(request.getRemoteUser()));
        return "user";
    }
}
