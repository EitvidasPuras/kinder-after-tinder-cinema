package com.kindercinema.kinderaftertindercinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KinderController {

    @GetMapping("/kinder")
    public String openKinderPage(Model model) {
        return "Kinder/kinderPage";
    }

    @GetMapping("/kinder/profile")
    public String openKinderProfilePage(Model model) {
        return "Kinder/profilePage";
    }

}
