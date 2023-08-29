package com.ifsju.interfaceweb.controller;

import com.ifsju.interfaceweb.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send/email")
    public String sendEmail(@RequestParam String email) throws Exception{
        return emailService.sendMessage(email);
    }
}
