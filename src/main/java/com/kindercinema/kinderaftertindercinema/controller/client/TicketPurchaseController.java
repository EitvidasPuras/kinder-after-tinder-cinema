package com.kindercinema.kinderaftertindercinema.controller.client;

import com.kindercinema.kinderaftertindercinema.entity.Movie;
import com.kindercinema.kinderaftertindercinema.entity.Session;
import com.kindercinema.kinderaftertindercinema.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/session")
public class TicketPurchaseController {

    private final SessionRepository sessionRepository;

    @Autowired
    public TicketPurchaseController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/{session_id}")
    public String openTicketPurchasePage(@PathVariable int sessionId, Model model) {
        Session session = sessionRepository.findById(sessionId).get();
        Movie movie = session.getMovie();


        return "client/ticketPurchasePage";
    }


}
