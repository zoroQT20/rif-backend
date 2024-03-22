package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RiskFormService {

    @Autowired
    private RiskFormRepository riskFormRepository;

    @Autowired
    private ReportRepository reportRepository;

    // Save a list of form data as part of a single report
    public void saveRiskFormDataList(List<RiskFormData> formDataList) {
        Report report = new Report();
        for (RiskFormData formData : formDataList) {
            report.addRiskFormData(formData);
        }
        reportRepository.save(report);
    }
}
