package com.ifsju.interfaceweb.controller;

import com.ifsju.interfaceweb.dto.EmailDTO;
import com.ifsju.interfaceweb.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class EmailController {
    private final EmailService emailService;
    
    private final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @Tag(name = "sign-up")
    @PostMapping("/send/email")
    public String sendEmail(@RequestBody EmailDTO emailDTO) throws Exception{
        LOGGER.info("[sendEmail] " + emailDTO.getEmail() + "로 메일 발송");

        return emailService.sendMessage(emailDTO.getEmail());
    }
}
