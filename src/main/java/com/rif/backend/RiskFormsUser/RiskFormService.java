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

    @Autowired
    private ResponsiblePersonRepository responsiblePersonRepository;

    @Transactional
    public void saveRiskFormDataList(List<RiskFormData> formDataList) {
        if (!formDataList.isEmpty()) {
            // Create only one Report for all formData in the list
            Report report = new Report();
            reportRepository.save(report);

            for (RiskFormData formData : formDataList) {
                formData.setReport(report);  // Set the same report for each formData

                // Handling opportunities and action plans
                formData.getOpportunities().forEach(opportunity -> opportunity.setRiskFormData(formData));
                formData.getActionPlans().forEach(actionPlan -> actionPlan.setRiskFormData(formData));
                                formData.getRiskParticulars().forEach(riskParticular -> riskParticular.setRiskFormData(formData));


                // Convert responsiblePerson names to entities
                formData.convertNamesToResponsiblePersons();

                // Save the formData with associated entities
                riskFormRepository.save(formData);
            }
        }
    }

    // You may add additional methods to handle other business logic like updating or deleting RiskFormData
}
