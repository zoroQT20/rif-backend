package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.rif.backend.Auth.User;
import com.rif.backend.Auth.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<RiskFormDataGroupedDTO> getRiskFormDataGroupedBySdaNumber() {
        List<Object[]> results = riskFormRepository.findGroupedBySdaNumber();
        return results.stream().map(result -> new RiskFormDataGroupedDTO(
            (Integer) result[0],
            (String) result[1],
            Arrays.asList(((String) result[2]).split(",")),
            (String) result[3],
            (String) result[4]
        )).collect(Collectors.toList());
    }

    @Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
    public List<RiskFormDataCustomDTO> getRiskFormDataBySdaNumber(Integer sdaNumber) {
        List<Object[]> results = riskFormRepository.findRiskFormDataBySdaNumber(sdaNumber);
        return results.stream()
                .map(result -> new RiskFormDataCustomDTO((String) result[0], (String) result[1], (String) result[2]))
                .collect(Collectors.toList());
    }

    @Transactional(javax.transaction.Transactional.TxType.SUPPORTS)
    public List<PrerequisiteDataDTO> getAllRiskFormData() {
        List<Object[]> results = riskFormRepository.findAllRiskFormData();
        return results.stream()
                .map(result -> new PrerequisiteDataDTO((String) result[0], (Integer) result[1], (String) result[2], (String) result[3]))
                .collect(Collectors.toList());
    }
}
