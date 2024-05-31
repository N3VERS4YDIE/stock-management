package com.stock.management.web.controller;

import com.stock.management.web.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/email")
    public ResponseEntity<String> sendEmail(
        @RequestParam String to,
        @RequestParam String subject,
        @RequestParam String text
    ) {
        try {
            emailService.sendMessage(to, subject, text);
            return ResponseEntity.ok("Email sent");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
