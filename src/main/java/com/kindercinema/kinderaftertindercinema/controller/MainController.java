package com.kindercinema.kinderaftertindercinema.controller;

import com.kindercinema.kinderaftertindercinema.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public String test(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", user.getEmail());
        return "greetings";
    }

    @GetMapping("/home")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/movies")
    public String moviesPage() {
        return "movies/moviesPage";
    }
}
