package com.kindercinema.kinderaftertindercinema.controller.client;

import com.kindercinema.kinderaftertindercinema.entity.Seat;
import com.kindercinema.kinderaftertindercinema.entity.Session;
import com.kindercinema.kinderaftertindercinema.entity.Ticket;
import com.kindercinema.kinderaftertindercinema.repository.RowRepository;
import com.kindercinema.kinderaftertindercinema.repository.SeatRepository;
import com.kindercinema.kinderaftertindercinema.repository.SessionRepository;
import com.kindercinema.kinderaftertindercinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller("clientTicketPurchase")
@RequestMapping("/session")
public class TicketPurchaseController {

    private final SessionRepository sessionRepository;
    private final RowRepository rowRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public TicketPurchaseController(SessionRepository sessionRepository, RowRepository rowRepository,
                                    TicketRepository ticketRepository, SeatRepository seatRepository) {
        this.sessionRepository = sessionRepository;
        this.rowRepository = rowRepository;
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
    }

    @GetMapping("/{sessionId}")
    public String openTicketPurchasePage(@PathVariable int sessionId, Model model) {
        Session session = sessionRepository.findById(sessionId).get();
        model.addAttribute("sessionas", session);
        return "client/ticketPurchasePage";
    }


    @GetMapping("/row/{rowId}")
    @ResponseBody
    public List<Seat> getSeatsByRow(@PathVariable int rowId) {
        return rowRepository.findById(rowId).get().getSeats();
    }

    @PostMapping("/buy")
    public String buyTicket(@RequestParam("sessionId") int sessionId, @RequestParam("seat") int seatId) {
        Session session = sessionRepository.findById(sessionId).get();
        Seat seat = seatRepository.findById(seatId).get();
        Ticket ticket = new Ticket();
        ticket.setPayDate(new Date());
        ticket.setCanceled(false);
        ticket.setReserved(false);
        ticket.setSession(session);
        ticket.setSeat(seat);
        ticketRepository.saveAndFlush(ticket);
        return "redirect:/movies";
    }
}
