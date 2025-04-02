package com.example.sneaker.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sneaker.repositories.BrandRepository;
import com.example.sneaker.repositories.ProductRepository;
import com.example.sneaker.services.CategoryDataService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CategoryDataService categoryDataService;
	@Autowired
	private BrandRepository brandRepository; // Make sure it's "brandRepository"
	
	@Autowired ProductRepository productRepository;
	
    @GetMapping("/")
    public String home(HttpServletRequest request,Model model) {
    	Cookie[] cookies = request.getCookies();
        boolean isLoggedIn = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    // Assuming jwtService.validateToken(cookie.getValue()) is used elsewhere
                    isLoggedIn = true;
                    break;
                }
            }
        }
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("message", "Welcome to the Sneaker App!");
        model.addAttribute("brands",brandRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }
}