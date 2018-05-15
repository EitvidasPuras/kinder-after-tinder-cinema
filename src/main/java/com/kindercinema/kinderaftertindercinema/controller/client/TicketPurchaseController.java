package com.kindercinema.kinderaftertindercinema.controller.client;

import com.kindercinema.kinderaftertindercinema.entity.Seat;
import com.kindercinema.kinderaftertindercinema.entity.Session;
import com.kindercinema.kinderaftertindercinema.repository.RowRepository;
import com.kindercinema.kinderaftertindercinema.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("clientTicketPurchase")
@RequestMapping("/session")
public class TicketPurchaseController {

    private final SessionRepository sessionRepository;
    private final RowRepository rowRepository;

    @Autowired
    public TicketPurchaseController(SessionRepository sessionRepository, RowRepository rowRepository) {
        this.sessionRepository = sessionRepository;
        this.rowRepository = rowRepository;
    }

    @GetMapping("/{sessionId}")
    public String openTicketPurchasePage(@PathVariable int sessionId, Model model) {
        Session session = sessionRepository.findById(sessionId).get();
        model.addAttribute("sessionas", session);
        model.addAttribute("sessionMovie", session.getMovie());
        return "client/ticketPurchasePage";
    }

    @GetMapping("/id/{sessionId}")
    @ResponseBody
    public Session ss(@PathVariable String sessionId, Model model) {
        Session session = sessionRepository.findById(Integer.parseInt(sessionId)).get();

        return session;
    }

    @GetMapping("/row/{rowId}")
    @ResponseBody
    public List<Seat> getSeatsByRow(@PathVariable int rowId) {
        return rowRepository.findById(rowId).get().getSeats();
    }


}
