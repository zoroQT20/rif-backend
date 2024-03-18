package com.rif.backend.RiskFormsUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "risk_forms")
public class RiskFormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sdaNumber;
    private String uploadRIF;
    private String issueParticulars;
    private String issueType;
    private String riskParticulars;
    private int riskSEV;
    private int riskPROB;
    private String riskLevel;
    private String riskType;
    private String opportunities;
    private String actionPlan;
    private String date;
    private String responsiblePerson;
    private String actionRad;
    private String riskRating;

    // Constructors
    public RiskFormData() {
    }

    public RiskFormData(Integer sdaNumber, String uploadRIF, String issueParticulars, String issueType, String riskParticulars, int riskSEV, int riskPROB, String riskLevel, String riskType, String opportunities, String actionPlan, String date, String responsiblePerson, String actionRad, String riskRating) {
        this.sdaNumber = sdaNumber;
        this.uploadRIF = uploadRIF;
        this.issueParticulars = issueParticulars;
        this.issueType = issueType;
        this.riskParticulars = riskParticulars;
        this.riskSEV = riskSEV;
        this.riskPROB = riskPROB;
        this.riskLevel = riskLevel;
        this.riskType = riskType;
        this.opportunities = opportunities;
        this.actionPlan = actionPlan;
        this.date = date;
        this.responsiblePerson = responsiblePerson;
        this.actionRad = actionRad;
        this.riskRating = riskRating;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSdaNumber() {
        return sdaNumber;
    }

    public void setSdaNumber(Integer sdaNumber) {
        this.sdaNumber = sdaNumber;
    }

    public String getUploadRIF() {
        return uploadRIF;
    }

    public void setUploadRIF(String uploadRIF) {
        this.uploadRIF = uploadRIF;
    }

    public String getIssueParticulars() {
        return issueParticulars;
    }

    public void setIssueParticulars(String issueParticulars) {
        this.issueParticulars = issueParticulars;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getRiskParticulars() {
        return riskParticulars;
    }

    public void setRiskParticulars(String riskParticulars) {
        this.riskParticulars = riskParticulars;
    }

    public int getRiskSEV() {
        return riskSEV;
    }

    public void setRiskSEV(int riskSEV) {
        this.riskSEV = riskSEV;
    }

    public int getRiskPROB() {
        return riskPROB;
    }

    public void setRiskPROB(int riskPROB) {
        this.riskPROB = riskPROB;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(String opportunities) {
        this.opportunities = opportunities;
    }

    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getActionRad() {
        return actionRad;
    }

    public void setActionRad(String actionRad) {
        this.actionRad = actionRad;
    }

    public String getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(String riskRating) {
        this.riskRating = riskRating;
    }
}
