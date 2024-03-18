package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiskFormService {

    @Autowired
    private RiskFormRepository riskFormRepository;

    public void saveRiskFormData(RiskFormData formData) {
        riskFormRepository.save(formData);
    }
}
