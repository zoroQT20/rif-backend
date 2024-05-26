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

    @GetMapping("/groupedBySdaNumber")
    public ResponseEntity<List<RiskFormDataGroupedDTO>> getRiskFormDataGroupedBySdaNumber(
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate,
        @RequestParam(required = false) Boolean sortUnitAsc
    ) {
        List<RiskFormDataGroupedDTO> groupedData = riskFormService.getRiskFormDataGroupedBySdaNumber();

        if (startDate != null && endDate != null) {
            groupedData = groupedData.stream()
                .filter(data -> data.getSubmissionDate().compareTo(startDate) >= 0 && data.getSubmissionDate().compareTo(endDate) <= 0)
                .collect(Collectors.toList());
        }

        if (sortUnitAsc != null) {
            groupedData.sort((a, b) -> sortUnitAsc ? a.getUnit().compareTo(b.getUnit()) : b.getUnit().compareTo(a.getUnit()));
        }

        return ResponseEntity.ok(groupedData);
    }

    @GetMapping("/dataBySdaNumber/{sdaNumber}")
    public ResponseEntity<List<RiskFormDataCustomDTO>> getRiskFormDataBySdaNumber(@PathVariable Integer sdaNumber) {
        List<RiskFormDataCustomDTO> data = riskFormService.getRiskFormDataBySdaNumber(sdaNumber);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/allData")
    public ResponseEntity<List<PrerequisiteDataDTO>> getAllRiskFormData() {
        List<PrerequisiteDataDTO> data = riskFormService.getAllRiskFormData();
        return ResponseEntity.ok(data);
    }
}
