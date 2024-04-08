package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riskforms")
public class RiskFormController {

    @Autowired
    private RiskFormService riskFormService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitRiskForm(@RequestBody List<RiskFormData> formDataList) {
        try {
            // The saveRiskFormDataList method should now also handle the mapping of opportunities
            // and action plans to the corresponding RiskFormData objects
            riskFormService.saveRiskFormDataList(formDataList);
            return ResponseEntity.ok().body("Form submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to submit the form: " + e.getMessage());
        }
    }
}
