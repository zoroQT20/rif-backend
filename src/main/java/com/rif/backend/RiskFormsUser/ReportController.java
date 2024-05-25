package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/unitType/{unitType}")
    public ResponseEntity<List<ReportDTO>> getReportsByUnitType(@PathVariable String unitType) {
        List<Report> reports = reportService.getReportsByUnitType(unitType);
        List<ReportDTO> reportDTOs = reports.stream()
                                             .map(ReportDTO::new)
                                             .collect(Collectors.toList());
        return ResponseEntity.ok(reportDTOs);
    }

    @GetMapping("/unitTypeCount/{unitType}")
    public ResponseEntity<Long> getReportCountByUnitType(@PathVariable String unitType) {
        long count = reportService.getReportCountByUnitType(unitType);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/unitTypeCount/{unitType}/dateRange")
    public ResponseEntity<Long> getReportCountByUnitTypeAndDateRange(@PathVariable String unitType,
                                                                     @RequestParam String startDate,
                                                                     @RequestParam String endDate) {
        long count = reportService.getReportCountByUnitTypeAndDateRange(unitType, startDate, endDate);
        return ResponseEntity.ok(count);
    }
}
