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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/matching")
    public String openMatchingPage(Model model) {
        return "kinder/matchingPage";
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

    @PostMapping("/profile/save")
    public String saveProfile(@RequestParam("Phone_Number") String phoneNum, @RequestParam("Gender") byte gender, @RequestParam("Description") String description, @RequestParam("InterestedIn") byte interestedIn, @RequestParam("Age") int age) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KinderUser kinderUser = kinderUserRepository.findByUser(user);
        //Genre genre = genreRepository.findById(interestedGenre).get();
        if (kinderUser == null) {
            kinderUser = new KinderUser();
            kinderUser.setUser(user);
        }
        kinderUser.setPhoneNumber(phoneNum);
        kinderUser.setGender(gender);
        kinderUser.setDescription(description);
        kinderUser.setAge(age);
        kinderUserRepository.saveAndFlush(kinderUser);
        return "redirect:/kinder/profile";
    }

   /* @PostMapping("/profile/save")
    public String saveProfile(@RequestParam("Interested_in") byte interestedIn, @RequestParam("Interested_age") int interestedAge, @RequestParam("Interested_genre") int interestedGenre) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KinderUser kinderUser = kinderUserRepository.findByUser(user);
        Genre genre = genreRepository.findById(interestedGenre).get();
        kinderUser.setInterestedIn(interestedIn);
        kinderUser.setInterestedAge(interestedAge);
        kinderUser.setInterestedGenre(genre);
        kinderUserRepository.saveAndFlush(kinderUser);
        return "redirect:/kinder/interestsPage";
    }*/

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('CLIENT')")
    public String openKinderProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KinderUser kinderUser = kinderUserRepository.findByUser(user);
        model.addAttribute("kinder", kinderUser);
        return "kinder/profilePage";
    }

  /*  @GetMapping("/{age}")
    @ResponseBody
    public List<KinderUser> getMatchByAge(@PathVariable int age) throws ParseException {
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //Date currentDate = format.parse(date);
        //Date nextDate = getNextDay(currentDate);
        int ageFrom = age;
        return kinderUserRepository.getMatchByAge(ageFrom);
    }*/

    @GetMapping("/getAll")
    @ResponseBody
    public List<KinderUser> getAllUsers() {
        return kinderUserRepository.findAll();
    }


}
