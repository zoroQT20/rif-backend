package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
public class RiskFormService {

    @Autowired
    private RiskFormRepository riskFormRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Transactional
    public void saveRiskFormDataList(List<RiskFormData> formDataList) {
        Report report = new Report();
        reportRepository.save(report); // Save the report to generate an ID.

        for (RiskFormData formData : formDataList) {
            // Associate each RiskFormData with the report.
            formData.setReport(report);

            // Process opportunities
            if (formData.getOpportunities() != null) {
                for (Opportunity opportunity : formData.getOpportunities()) {
                    opportunity.setRiskFormData(formData); // Associate with RiskFormData
                }
            }

            // Process action plans
            if (formData.getActionPlans() != null) {
                for (ActionPlan actionPlan : formData.getActionPlans()) {
                    actionPlan.setRiskFormData(formData); // Associate with RiskFormData
                }
            }

            riskFormRepository.save(formData); // Save each RiskFormData along with its opportunities and action plans.
        }
    }
}
