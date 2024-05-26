package com.rif.backend.RiskFormsUser;

public class RiskFormDataCustomDTO {
    private String unitType;
    private String riskLevel;
    private String submissionDate;
    private String riskType; // Added field for Initial and Residual risks

    public RiskFormDataCustomDTO(String unitType, String riskLevel, String submissionDate, String riskType) {
        this.unitType = unitType;
        this.riskLevel = riskLevel;
        this.submissionDate = submissionDate;
        this.riskType = riskType;
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

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }
}
