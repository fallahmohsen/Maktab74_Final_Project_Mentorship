package com.example.online_quiz2.controller;

import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @GetMapping("/login")
    public String getLogin() {
        return "test";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @RequestMapping("/save-user")
    public String saveUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("name") String name,
                           @RequestParam("gender") String gender, @RequestParam("role") String role) throws FileNotFoundException {
        User user = new User(username, password, name, gender, "null", role);
        userService.save(user);
        return "login";
    }

    @GetMapping("/check-user")
    public String checkUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.checkUserLogin(username, password);
        return null;
    }

}
