package com.kindercinema.kinderaftertindercinema.controller.kinder;

import com.kindercinema.kinderaftertindercinema.entity.Genre;
import com.kindercinema.kinderaftertindercinema.entity.KinderUser;
import com.kindercinema.kinderaftertindercinema.entity.User;
import com.kindercinema.kinderaftertindercinema.repository.GenreRepository;
import com.kindercinema.kinderaftertindercinema.repository.KinderUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/kinder")
public class KinderController {

    private final KinderUserRepository kinderUserRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public KinderController(KinderUserRepository kinderUserRepository, GenreRepository genreRepository) {
        this.kinderUserRepository = kinderUserRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public String openKinderPage(Model model) {
        return "kinder/kinderPage";
    }


    @GetMapping("/kinderRegistration")
    public String openKinderRegistrationPage(Model model) {
        return "kinder/registrationPage";
    }

    @GetMapping("/interestsPage")
    public String openInterestsPage(Model model) {
        return "kinder/settingsPage";
    }

    @PostMapping("/interestsPage/save")
    public String saveInterests(@RequestParam("Interested_in") byte interestedIn, @RequestParam("Interested_age") int interestedAge, @RequestParam("Interested_genre") int interestedGenre) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KinderUser kinderUser = kinderUserRepository.findByUser(user);
        Genre genre = genreRepository.findById(interestedGenre).get();
        kinderUser.setInterestedIn(interestedIn);
        kinderUser.setInterestedAge(interestedAge);
        kinderUser.setInterestedGenre(genre);
        kinderUserRepository.saveAndFlush(kinderUser);
        return "redirect:/kinder/interestsPage";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public String openKinderProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KinderUser kinderUser = kinderUserRepository.findByUser(user);
        model.addAttribute("kinder", kinderUser);
        return "kinder/profilePage";
    }


}
