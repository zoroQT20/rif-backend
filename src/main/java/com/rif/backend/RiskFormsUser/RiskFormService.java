package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.rif.backend.Auth.User;
import com.rif.backend.Auth.UserRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class RiskFormService {

    @Autowired
    private RiskFormRepository riskFormRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ResponsiblePersonRepository responsiblePersonRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveRiskFormDataList(List<RiskFormData> formDataList) {
        if (!formDataList.isEmpty()) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

            Report report = new Report();
            report.setUser(user);
            reportRepository.save(report);

            for (RiskFormData formData : formDataList) {
                formData.setReport(report);

                formData.getOpportunities().forEach(opportunity -> opportunity.setRiskFormData(formData));
                formData.getActionPlans().forEach(actionPlan -> actionPlan.setRiskFormData(formData));
                formData.getRiskParticulars().forEach(riskParticular -> riskParticular.setRiskFormData(formData));
                formData.convertNamesToResponsiblePersons();

                riskFormRepository.save(formData);
            }
        }
    }
}
