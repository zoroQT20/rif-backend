package com.rif.backend.RiskFormsUser;

public class PrerequisiteDataDTO {
    private String unitType;
    private Integer sdaNumber;
    private String riskLevel;
    private String submissionDate;

    public PrerequisiteDataDTO(String unitType, Integer sdaNumber, String riskLevel, String submissionDate) {
        this.unitType = unitType;
        this.sdaNumber = sdaNumber;
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

    public Integer getSdaNumber() {
        return sdaNumber;
    }

    public void setSdaNumber(Integer sdaNumber) {
        this.sdaNumber = sdaNumber;
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
