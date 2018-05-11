package com.kindercinema.kinderaftertindercinema.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/sessions")
public class SessionController {

    @GetMapping
    public String openSessionPage() {
        return "manager/sessionPage";
    }
}
