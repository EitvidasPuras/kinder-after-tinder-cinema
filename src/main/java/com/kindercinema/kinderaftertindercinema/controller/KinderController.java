package com.kindercinema.kinderaftertindercinema.controller;

import com.kindercinema.kinderaftertindercinema.entity.KinderUser;
import com.kindercinema.kinderaftertindercinema.entity.User;
import com.kindercinema.kinderaftertindercinema.repository.KinderUserRepository;
import com.kindercinema.kinderaftertindercinema.repository.MovieRepository;
import com.kindercinema.kinderaftertindercinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kinder")
public class KinderController {

    private final KinderUserRepository kinderUserRepository;

    @Autowired
    public KinderController(KinderUserRepository kinderUserRepository) {
        this.kinderUserRepository = kinderUserRepository;
    }

    @GetMapping
    public String openKinderPage(Model model) {
        return "kinder/kinderPage";
    }


    @GetMapping("/kinderRegistration")
    public String openKinderRegistrationPage(Model model) {
        return "kinder/registrationPage";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public String openKinderProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KinderUser kinderUser = kinderUserRepository.findByUser(user);
        model.addAttribute("kinder",  kinderUser);
        return "kinder/profilePage";
    }



}
