package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Report> report = reportRepository.findByIdWithRiskForms(id);
        if (report.isPresent()) {
            return ResponseEntity.ok(new ReportDTO(report.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reports")
    public ResponseEntity<?> getReportsByUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Report> reports = reportRepository.findAllByUserEmail(email);
        List<ReportDTO> reportDTOs = reports.stream().map(ReportDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(reportDTOs);
    }
}
