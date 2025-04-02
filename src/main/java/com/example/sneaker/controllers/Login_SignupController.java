package com.example.sneaker.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sneaker.repositories.UserRepository;

@Controller
public class Login_SignupController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/user-login")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Sneaker App!");
      //  model.addAttribute("users",userRepository.findAll());
        return "login-signup";
    }
}
