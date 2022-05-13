package com.code.registration.controller;

import com.code.registration.model.User;
import com.code.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {
    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerRequest",new User());
        return "register";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginRequest", new User());
        return "login";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){
        System.out.println("registerRequest" + user);
        var registerUser = userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        if (registerUser != null){
            return "login";
        }
        else{
            return null;
        }
    }
    @PostMapping("/login")
    public String loign(@ModelAttribute User user, Model model){
        System.out.println("loginRequest" + user);
        var loginUser = userService.authenticate(user.getUsername(),user.getPassword());
        if (loginUser!=null){
            model.addAttribute("userName",loginUser.getUsername());
            return "personal";
        }
        else {
            return null;
        }
    }
}
