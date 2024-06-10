package com.rif.backend.RiskFormsUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.rif.backend.Auth.User;
import com.rif.backend.Auth.UserRepository;
import com.rif.backend.Approver.Approver;
import com.rif.backend.Approver.ApproverRepository;
import com.rif.backend.Auth.EmailService;
import com.rif.backend.Esignature.ESignature;
import com.rif.backend.Esignature.ESignatureService;
import com.rif.backend.Prerequisites.Prerequisite;
import com.rif.backend.Prerequisites.PrerequisiteService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ApproverRepository approverRepository;
    @Autowired
    private PrerequisiteService prerequisiteService;
    @Autowired
    private ESignatureService eSignatureService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RiskFormRepository riskFormRepository;
    @Autowired
    private EmailService emailService;

    @Transactional
    public void updateProofAndNotes(Long reportId, List<MultipartFile> pdfProofs, List<String> notes) throws IOException {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));

        List<RiskFormData> riskFormDataList = report.getRiskFormData();

        for (int i = 0; i < riskFormDataList.size(); i++) {
            RiskFormData riskFormData = riskFormDataList.get(i);

            if (pdfProofs != null && pdfProofs.size() > i && pdfProofs.get(i) != null && !pdfProofs.get(i).isEmpty()) {
                MultipartFile pdfProof = pdfProofs.get(i);
                byte[] pdfBytes = pdfProof.getBytes();
                riskFormData.setPdfProof(pdfBytes);
            }

            if (notes != null && notes.size() > i) {
                riskFormData.setNotes(notes.get(i));
            }

            riskFormRepository.save(riskFormData);
        }
    }

    @Transactional(readOnly = true)
    public Optional<byte[]> getPdfProof(Long reportId, Long riskFormDataId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        return report.getRiskFormData().stream()
            .filter(riskFormData -> riskFormData.getId().equals(riskFormDataId))
            .map(RiskFormData::getPdfProof)
            .findFirst();
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

    @Transactional
    public void approveReport(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setStatus(Report.ReportStatus.APPROVER_APPROVED);
        report.setApproverApproveDate(LocalDate.now());
        report.setApproverComment(null);
        reportRepository.save(report);

        // Send approval email
        emailService.sendApprovalEmail(report.getUser().getEmail(), report.getId(), report.getApproverApproveDate());
    }

    @Transactional
    public void markReportForRevision(Long reportId, String comment) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setStatus(Report.ReportStatus.APPROVER_FOR_REVISION);
        report.setApproverComment(comment);
        report.setApproverApproveDate(LocalDate.now());  // Set the date here
        reportRepository.save(report);

        // Send revision email
        emailService.sendRevisionEmail(report.getUser().getEmail(), report.getId(), comment, report.getApproverApproveDate());
    }

    @Transactional
    public void verifyReport(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setStatus(Report.ReportStatus.ADMIN_VERIFIED);
        reportRepository.save(report);

        // Send verification email
        emailService.sendVerificationEmail(report.getUser().getEmail(), report.getId(), LocalDate.now());
    }

    // New functions for admin-specific email notifications

    @Transactional
    public void adminVerifyReport(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setStatus(Report.ReportStatus.ADMIN_VERIFIED);
        reportRepository.save(report);

        // Send verification email and notifications
        emailService.sendAdminVerificationEmail(report.getUser().getEmail(), report.getId(), LocalDate.now());
    }

    @Transactional
    public void adminMarkReportForRevision(Long reportId, String comment) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        report.setStatus(Report.ReportStatus.ADMIN_FOR_REVISION);
        report.setAdminComment(comment);
        report.setApproverApproveDate(LocalDate.now());  // Set the date here
        reportRepository.save(report);

        // Send revision email and notifications
        emailService.sendAdminRevisionEmail(report.getUser().getEmail(), report.getId(), comment, LocalDate.now());
    }

    @Transactional(readOnly = true)
    public ApproverDetailsDTO getApproverDetails(Long reportId) {
        // Fetch the prerequisite unit based on the reportId
        String prerequisiteUnit = reportRepository.findPrerequisiteUnitByReportId(reportId)
                .orElseThrow(() -> new RuntimeException("Prerequisite unit not found for report ID " + reportId));

        // Find the approver based on the prerequisite unit
        Approver approver = approverRepository.findByApproverUnit(prerequisiteUnit)
                .orElseThrow(() -> new RuntimeException("Approver not found for unit " + prerequisiteUnit));

        // Map the approver entity to approver details DTO
        return new ApproverDetailsDTO(
                approver.getProfessionalTitle(),
                approver.getPostNominalTitle(),
                approver.getApproverPhoto(),
                approver.getUser().getFirstname(),
                approver.getUser().getLastname()
        );
    }
}
