package com.rif.backend.RiskFormsUser;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/riskforms")
@Validated
public class RiskIdentificationFormController {

    private static final Logger logger = LoggerFactory.getLogger(RiskIdentificationFormController.class);

    @Autowired
    private RiskIdentificationFormRepository repository;

    @PostMapping("/submit")
public ResponseEntity<?> createRiskForm(@Valid @RequestBody RiskIdentificationFormEntity riskIdentificationForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        // Constructing error message
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMessage.append(error.getDefaultMessage()).append("\n");
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

    try {
        // Save the form
        RiskIdentificationFormEntity savedForm = repository.save(riskIdentificationForm);
        return ResponseEntity.ok(savedForm);
    } catch (Exception e) {
        // Log and return error response
        logger.error("Error occurred while saving risk form", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create risk form");
    }
}

    // Other controller methods...
}
