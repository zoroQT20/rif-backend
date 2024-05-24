package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/riskforms")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("reportId") Long reportId,
                                            @RequestParam("file") MultipartFile file,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            reportService.savePdf(reportId, file);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed.");
        }
    }
}
