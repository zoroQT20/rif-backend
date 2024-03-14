package com.rif.backend.RiskFormsUser;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "riskform")
public class RiskIdentificationFormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id;

    @NotNull
    @Column(name = "SDA_number")
    private Integer sdaNumber;

    @NotEmpty
    @Column(name = "issue_particulars")
    private String issueParticulars;

    @NotEmpty
    @Column(name = "issue_type")
    private String issueType;

    @NotEmpty
    @Column(name = "risk_particulars")
    private String riskParticulars;

    @NotNull
    @Column(name = "SEV")
    private Integer sev;

    @NotNull
    @Column(name = "PROB")
    private Integer prob;

    @NotNull
    @Column(name = "risk_rating")
    private Integer riskRating;

    @NotEmpty
    @Column(name = "risk_categorization_level")
    private String riskCategorizationLevel;

    @NotEmpty
    @Column(name = "risk_categorization_type")
    private String riskCategorizationType;

    @Column(name = "opportunities")
    private String opportunities;

    @Column(name = "action_plans")
    private String actionPlans;

    @NotEmpty
    @Column(name = "person_responsible")
    private String personResponsible;

    @NotEmpty
    @Column(name = "action_type")
    private String actionType;

    @NotNull
    @Column(name = "submission_date")
    private Date submissionDate;

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

    public Integer getSev() {
        return sev;
    }

    public void setSev(Integer sev) {
        this.sev = sev;
    }

    public Integer getProb() {
        return prob;
    }

    public void setProb(Integer prob) {
        this.prob = prob;
    }

    public Integer getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(Integer riskRating) {
        this.riskRating = riskRating;
    }

    public String getRiskCategorizationLevel() {
        return riskCategorizationLevel;
    }

    public void setRiskCategorizationLevel(String riskCategorizationLevel) {
        this.riskCategorizationLevel = riskCategorizationLevel;
    }

    public String getRiskCategorizationType() {
        return riskCategorizationType;
    }

    public void setRiskCategorizationType(String riskCategorizationType) {
        this.riskCategorizationType = riskCategorizationType;
    }

    public String getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(String opportunities) {
        this.opportunities = opportunities;
    }

    public String getActionPlans() {
        return actionPlans;
    }

    public void setActionPlans(String actionPlans) {
        this.actionPlans = actionPlans;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        this.personResponsible = personResponsible;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
}
