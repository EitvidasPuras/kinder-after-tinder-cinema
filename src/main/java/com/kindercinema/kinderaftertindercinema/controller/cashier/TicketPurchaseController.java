package com.kindercinema.kinderaftertindercinema.controller.cashier;

import com.kindercinema.kinderaftertindercinema.entity.Hall;
import com.kindercinema.kinderaftertindercinema.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("cashierTicketPurchaseController")
@RequestMapping("/cashier/ticketpurchase")
public class TicketPurchaseController {

    private final SessionRepository sessionRepository;

    @Autowired
    public TicketPurchaseController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    public String ticketPurchase() {
        return "cashier/ticketPurchasePage";
    }

    @GetMapping("/hall/{sessionId}")
    @ResponseBody
    public Hall getHallBySessionId(@PathVariable int sessionId) {
        return sessionRepository.findById(sessionId).get().getHall();
    }
}
