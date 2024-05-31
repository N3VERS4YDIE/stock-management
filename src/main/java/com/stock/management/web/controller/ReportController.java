package com.stock.management.web.controller;

import com.stock.management.web.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport(@RequestParam String sql) {
        try {
            byte[] report = reportService.generateReport(sql);
            return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(report);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage().getBytes());
        }
    }
}
