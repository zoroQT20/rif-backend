package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rif.backend.Prerequisites.Prerequisite;
import com.rif.backend.Prerequisites.PrerequisiteService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private PrerequisiteService prerequisiteService;

    

    @Transactional
    public void savePdf(Long reportId, MultipartFile file) throws IOException {
        Optional<Report> optionalReport = reportRepository.findById(reportId);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            report.setPdfBlob(file.getBytes());
            reportRepository.save(report);
        } else {
            throw new RuntimeException("Report not found with id " + reportId);
        }
    }

    @Transactional(readOnly = true)
    public List<Report> getReportsByUnitType(String unitType) {
        return reportRepository.findAllByUnitType(unitType);
    }

    @Transactional(readOnly = true)
    public long getReportCountByUnitType(String unitType) {
        return reportRepository.countByUnitType(unitType);
    }

    @Transactional(readOnly = true)
    public long getReportCountByUnitTypeAndDateRange(String unitType, String startDate, String endDate) {
        return reportRepository.countByUnitTypeAndDateRange(unitType, startDate, endDate);
    }
      @Transactional(readOnly = true)
    public List<Report> getReportsByUserUnitOrApproverUnit(String unit, String email) {
        return reportRepository.findAllByUserUnitOrApproverUnit(unit, email);
    }

        @Transactional(readOnly = true)
    public ReportDetailsDTO getReportWithDetails(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        Prerequisite prerequisite = prerequisiteService.getPrerequisiteByUserId(report.getUser().getId()).orElse(null);
        return new ReportDetailsDTO(report, prerequisite);
    }

}
