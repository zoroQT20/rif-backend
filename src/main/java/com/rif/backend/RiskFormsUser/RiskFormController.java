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

    /**
     * Endpoint to submit a list of RiskFormData.
     * @param formDataList A list of RiskFormData.
     * @return ResponseEntity with success or error message.
     */
@PostMapping("/submit")
public ResponseEntity<?> submitRiskForm(@RequestBody List<RiskFormData> formDataList) {
    System.out.println("Received form data: " + formDataList); // Log incoming data for debugging
    try {
        riskFormService.saveRiskFormDataList(formDataList);
        return ResponseEntity.ok("Form submitted successfully!");
    } catch (Exception e) {
        e.printStackTrace(); // Print stack trace for detailed error analysis
        return ResponseEntity.badRequest().body("Failed to submit the form: " + e.getMessage());
    }
}



    /**
     * Endpoint to retrieve a report with its RiskFormDatas by report ID.
     * @param id The ID of the report.
     * @return ResponseEntity with a Report object or Not Found status.
     */
    @GetMapping("/report/{id}")
    public ResponseEntity<?> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            return ResponseEntity.ok(report.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add other endpoints as needed
}
