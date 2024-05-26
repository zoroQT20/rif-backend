package com.rif.backend.RiskFormsUser;

public class RiskFormDataCustomDTO {
    private String unitType;
    private String riskLevel;
    private String submissionDate;

    public RiskFormDataCustomDTO(String unitType, String riskLevel, String submissionDate) {
        this.unitType = unitType;
        this.riskLevel = riskLevel;
        this.submissionDate = submissionDate;
    }

    // Getters and Setters
    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }
}
