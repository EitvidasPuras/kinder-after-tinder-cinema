package com.kindercinema.kinderaftertindercinema.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @GetMapping("/test/{name}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public String test(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "greetings";
    }
}
