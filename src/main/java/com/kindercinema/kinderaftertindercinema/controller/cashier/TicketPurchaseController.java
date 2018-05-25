package com.kindercinema.kinderaftertindercinema.controller.cashier;

import com.kindercinema.kinderaftertindercinema.entity.Hall;
import com.kindercinema.kinderaftertindercinema.entity.Seat;
import com.kindercinema.kinderaftertindercinema.entity.Session;
import com.kindercinema.kinderaftertindercinema.entity.Ticket;
import com.kindercinema.kinderaftertindercinema.repository.SeatRepository;
import com.kindercinema.kinderaftertindercinema.repository.SessionRepository;
import com.kindercinema.kinderaftertindercinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller("cashierTicketPurchaseController")
@RequestMapping("/cashier/ticketpurchase")
public class TicketPurchaseController {

    private final SessionRepository sessionRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public TicketPurchaseController(SessionRepository sessionRepository,
                                    TicketRepository ticketRepository,
                                    SeatRepository seatRepository) {
        this.sessionRepository = sessionRepository;
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
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

    @PostMapping("/save")
    public String saveTicket(@RequestParam("sessionIdName") int sessionId, @RequestParam("seatSelect") int seatId) {
        Seat seat = seatRepository.findById(seatId).get();
        Session session = sessionRepository.findById(sessionId).get();
        Ticket ticket = new Ticket();
        ticket.setPayDate(new Date());
        ticket.setCanceled(false);
        ticket.setReserved(false);
        ticket.setSession(session);
        ticket.setSeat(seat);
        ticketRepository.saveAndFlush(ticket);
        return "redirect:/cashier/ticketpurchase";
    }
}
