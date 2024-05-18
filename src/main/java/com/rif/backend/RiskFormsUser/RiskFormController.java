package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/riskforms")
public class RiskFormController {

    @Autowired
    private RiskFormService riskFormService;

    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/submit")
    public ResponseEntity<?> submitRiskForm(@RequestBody List<RiskFormData> formDataList) {
        try {
            riskFormService.saveRiskFormDataList(formDataList);
            return ResponseEntity.ok("Form submitted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to submit the form: " + e.getMessage());
        }
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            return ResponseEntity.ok(report.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
