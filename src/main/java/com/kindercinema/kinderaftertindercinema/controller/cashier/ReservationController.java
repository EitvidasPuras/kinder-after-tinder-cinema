package com.kindercinema.kinderaftertindercinema.controller.cashier;

import com.kindercinema.kinderaftertindercinema.entity.Ticket;
import com.kindercinema.kinderaftertindercinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("cashierReservationController")
@RequestMapping("/cashier/reservations")
public class ReservationController {

    private final TicketRepository ticketRepository;

    @Autowired
    public ReservationController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public String openReservationPage(Model model) {
        List<Ticket> tickets = ticketRepository.findTicketsByReservedOrderByPayDate(true);
        model.addAttribute("tickets", tickets);
        return "cashier/reservationPage";
    }

    @PostMapping("/delete")
    public String deleteReservation(@RequestParam("id") int id) {
        ticketRepository.deleteById(id);
        return "redirect:/cashier/reservations";
    }
}
