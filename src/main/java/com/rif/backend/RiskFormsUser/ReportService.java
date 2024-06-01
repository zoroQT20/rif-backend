package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.rif.backend.Auth.User;
import com.rif.backend.Esignature.ESignature;
import com.rif.backend.Prerequisites.Prerequisite;
import com.rif.backend.Prerequisites.PrerequisiteService;
import com.rif.backend.Esignature.ESignatureService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private PrerequisiteService prerequisiteService;
       @Autowired
    private ESignatureService eSignatureService;

    
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
    public Optional<Report> findById(Long reportId) {
        return reportRepository.findById(reportId);
    }

   @Transactional(readOnly = true)
    public ReportDetailsDTO getReportWithDetails(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        Prerequisite prerequisite = prerequisiteService.getPrerequisiteByUserId(report.getUser().getId()).orElse(null);
        User user = report.getUser();
        ESignature esignature = eSignatureService.getESignatureByUserId(user.getId()).orElse(null);

        if (esignature == null) {
            throw new RuntimeException("Esignature not found");
        }

        return new ReportDetailsDTO(report, prerequisite, user, esignature);
    }

}
