package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.rif.backend.Auth.User;
import com.rif.backend.Esignature.ESignature;
import com.rif.backend.Esignature.ESignatureService;
import com.rif.backend.Prerequisites.Prerequisite;
import com.rif.backend.Prerequisites.PrerequisiteService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/riskforms")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepository reportRepository;

        @Autowired
    private ESignatureService eSignatureService;
    
    
        @Autowired
    private PrerequisiteService prerequisiteService;




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

    @GetMapping("/all-reports")
    public ResponseEntity<?> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        List<ReportDTO> reportDTOs = reports.stream().map(ReportDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(reportDTOs);
    }

        @GetMapping("/unit/{unit}/email/{email}")
    public ResponseEntity<List<ReportDTO>> getReportsByUserUnitOrApproverUnit(@PathVariable String unit, @PathVariable String email) {
        List<Report> reports = reportService.getReportsByUserUnitOrApproverUnit(unit, email);
        List<ReportDTO> reportDTOs = reports.stream()
                                             .map(ReportDTO::new)
                                             .collect(Collectors.toList());
        return ResponseEntity.ok(reportDTOs);
    }

   @GetMapping("/reportDetails/{reportId}")
    public ResponseEntity<ReportDetailsDTO> getReportWithDetails(@PathVariable Long reportId) {
        Report report = reportService.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        Prerequisite prerequisite = prerequisiteService.getPrerequisiteByUserId(report.getUser().getId()).orElse(null);
        User user = report.getUser();
        ESignature esignature = eSignatureService.getESignatureByUserId(user.getId()).orElse(null);

        if (esignature == null) {
            return ResponseEntity.notFound().build();
        }

        ReportDetailsDTO reportDetails = new ReportDetailsDTO(report, prerequisite, user, esignature);
        return ResponseEntity.ok(reportDetails);
    }
}
