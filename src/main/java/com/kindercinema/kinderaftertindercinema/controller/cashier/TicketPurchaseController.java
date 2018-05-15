package com.kindercinema.kinderaftertindercinema.controller.cashier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cashier/ticketpurchase")
public class TicketPurchaseController {

    @GetMapping
    public String ticketPurchase() {
        return "cashier/ticketPurchasePage";
    }
}
