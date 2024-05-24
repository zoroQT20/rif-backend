package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

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
}
