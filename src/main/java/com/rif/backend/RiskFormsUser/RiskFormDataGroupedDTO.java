package com.rif.backend.RiskFormsUser;

import java.util.List;

public class RiskFormDataGroupedDTO {
    private Integer sdaNumber;
    private String issueParticulars;
    private List<String> riskParticularDescriptions;
    private String unit;
    private String submissionDate;

    public RiskFormDataGroupedDTO(Integer sdaNumber, String issueParticulars, List<String> riskParticularDescriptions, String unit, String submissionDate) {
        this.sdaNumber = sdaNumber;
        this.issueParticulars = issueParticulars;
        this.riskParticularDescriptions = riskParticularDescriptions;
        this.unit = unit;
        this.submissionDate = submissionDate;
    }

    // Getters and Setters
    public Integer getSdaNumber() {
        return sdaNumber;
    }

    public void setSdaNumber(Integer sdaNumber) {
        this.sdaNumber = sdaNumber;
    }

    public String getIssueParticulars() {
        return issueParticulars;
    }

    public void setIssueParticulars(String issueParticulars) {
        this.issueParticulars = issueParticulars;
    }

    public List<String> getRiskParticularDescriptions() {
        return riskParticularDescriptions;
    }

    public void setRiskParticularDescriptions(List<String> riskParticularDescriptions) {
        this.riskParticularDescriptions = riskParticularDescriptions;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }
}
