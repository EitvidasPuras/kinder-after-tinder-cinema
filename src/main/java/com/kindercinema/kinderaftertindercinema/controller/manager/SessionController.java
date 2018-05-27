package com.kindercinema.kinderaftertindercinema.controller.manager;

import com.kindercinema.kinderaftertindercinema.entity.Session;
import com.kindercinema.kinderaftertindercinema.repository.HallRepository;
import com.kindercinema.kinderaftertindercinema.repository.MovieRepository;
import com.kindercinema.kinderaftertindercinema.repository.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/manager/sessions")
public class SessionController {

    private final SessionRepository sessionRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;

    public SessionController(SessionRepository sessionRepository, HallRepository hallRepository, MovieRepository movieRepository) {
        this.sessionRepository = sessionRepository;
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String openSessionPage(Model model) {
        model.addAttribute("halls", hallRepository.findAll());
        return "manager/sessionPage";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteSession(@RequestParam int id) {
        sessionRepository.deleteById(id);
        return "success";
    }

    @PostMapping("/new")
    @ResponseBody
    public Session newSession(@RequestParam String date, @RequestParam String startTime, @RequestParam String endTime, @RequestParam double price, @RequestParam int hallId, @RequestParam int movieId) throws ParseException {
        Session session = new Session();
        session.setStartDate(formatDate(date, startTime));
        session.setEndDate(formatDate(date, endTime));
        session.setPrice(price);
        session.setHall(hallRepository.findById(hallId).get());
        session.setMovie(movieRepository.findById(movieId).get());
        sessionRepository.saveAndFlush(session);
        return session;
    }

    private Date formatDate(String date, String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        return sdf.parse(date + " " + time);
    }
}
