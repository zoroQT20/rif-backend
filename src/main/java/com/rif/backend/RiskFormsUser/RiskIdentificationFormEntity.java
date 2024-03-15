package com.rif.backend.RiskFormsUser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "riskform")
public class RiskIdentificationFormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id;

    @NotBlank(message = "SDA number is required")
    @Column(name = "SDA_number")
    private String sdaNumber;

    @NotBlank(message = "Issue particulars are required")
    @Size(max = 255, message = "Issue particulars must be less than or equal to 255 characters")
    @Column(name = "issue_particulars")
    private String issueParticulars;

    @NotBlank(message = "Issue type is required")
    @Column(name = "issue_type")
    private String issueType;

    @NotBlank(message = "Risk particulars are required")
    @Column(name = "risk_particulars")
    private String riskParticulars;

    @NotNull(message = "Risk SEV is required")
    @Column(name = "SEV")
    private int riskSEV;

    @NotNull(message = "Risk PROB is required")
    @Column(name = "PROB")
    private int riskPROB;

    @NotBlank(message = "Risk rating is required")
    @Column(name = "risk_rating")
    private String riskRating;

    @NotBlank(message = "Risk categorization level is required")
    @Column(name = "risk_categorization_level")
    private String riskLevel;

    @NotBlank(message = "Risk categorization type is required")
    @Column(name = "risk_categorization_type")
    private String riskType;

    @NotBlank(message = "Opportunities are required")
    @Column(name = "opportunities")
    private String opportunities;

    @NotBlank(message = "Action plans are required")
    @Column(name = "action_plans", nullable = false)
    private String actionPlan;

    @NotNull(message = "Submission date is required")
    @Column(name = "submission_date")
    private LocalDate date;

    @NotBlank(message = "Person responsible is required")
    @Column(name = "person_responsible")
    private String responsiblePerson;

    @NotBlank(message = "Action type is required")
    @Column(name = "action_type")
    private String actionRad;

    // Constructors, Getters, and Setters
    // Constructors
    public RiskIdentificationFormEntity() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSdaNumber() {
        return sdaNumber;
    }

    public void setSdaNumber(String sdaNumber) {
        this.sdaNumber = sdaNumber;
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

    public String getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(String riskRating) {
        this.riskRating = riskRating;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
}
