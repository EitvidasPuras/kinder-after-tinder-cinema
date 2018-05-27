package com.kindercinema.kinderaftertindercinema.controller.cashier;

import com.kindercinema.kinderaftertindercinema.entity.Seat;
import com.kindercinema.kinderaftertindercinema.entity.Session;
import com.kindercinema.kinderaftertindercinema.entity.Ticket;
import com.kindercinema.kinderaftertindercinema.repository.SeatRepository;
import com.kindercinema.kinderaftertindercinema.repository.SessionRepository;
import com.kindercinema.kinderaftertindercinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller("cashierReservationController")
@RequestMapping("/cashier/reservations")
public class ReservationController {

    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public ReservationController(TicketRepository ticketRepository, SessionRepository sessionRepository, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.sessionRepository = sessionRepository;
        this.seatRepository = seatRepository;
    }

    @GetMapping
    public String openReservationPage(Model model) {
        List<Ticket> tickets = ticketRepository.findTicketsByReservedOrderByPayDateDesc(true);
        model.addAttribute("tickets", tickets);
        return "cashier/reservationPage";
    }

    @PostMapping("/delete")
    public String deleteReservation(@RequestParam("id") int id) {
        ticketRepository.deleteById(id);
        return "redirect:/cashier/reservations";
    }

    @GetMapping("/create")
    public String openReservationCreationPage() {
        return "cashier/createReservationPage";
    }

    @PostMapping("/save")
    public String createReservation(@RequestParam("sessionIdName") int sessionId, @RequestParam("seatSelect") int seatId) {
        Seat seat = seatRepository.findById(seatId).get();
        Session session = sessionRepository.findById(sessionId).get();
        Ticket ticket = new Ticket();
        ticket.setPayDate(new Date());
        ticket.setCanceled(false);
        ticket.setReserved(true);
        ticket.setSession(session);
        ticket.setSeat(seat);
        ticketRepository.saveAndFlush(ticket);
        return "redirect:/cashier/reservations";
    }
}
