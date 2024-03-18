package com.rif.backend.RiskFormsUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/riskforms")
public class RiskFormController {

    @Autowired
    private RiskFormService riskFormService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitRiskForm(@RequestBody RiskFormData formData) {
        riskFormService.saveRiskFormData(formData);
        return ResponseEntity.ok().build();
    }
}