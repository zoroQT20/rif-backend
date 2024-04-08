package com.rif.backend.RiskFormsUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Integer riskSEV;
    private Integer riskPROB;
    private String riskLevel;
    private String riskType;
    private String date;
    private String responsiblePerson;
    private String actionRad;
    private Integer riskRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToMany(mappedBy = "riskFormData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opportunity> opportunities = new ArrayList<>();

    @OneToMany(mappedBy = "riskFormData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActionPlan> actionPlans = new ArrayList<>();

    // Constructors
    public RiskFormData() {
    }

    // Getters and Setters
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

    public Integer getRiskSEV() {
        return riskSEV;
    }

    public void setRiskSEV(Integer riskSEV) {
        this.riskSEV = riskSEV;
    }

    public Integer getRiskPROB() {
        return riskPROB;
    }

    public void setRiskPROB(Integer riskPROB) {
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

  public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }

    public List<ActionPlan> getActionPlans() {
        return actionPlans;
    }

    public void setActionPlans(List<ActionPlan> actionPlans) {
        this.actionPlans = actionPlans;
    }

    // Helper methods to add and remove opportunities and action plans if needed
    public void addOpportunity(Opportunity opportunity) {
        opportunities.add(opportunity);
        opportunity.setRiskFormData(this);
    }

    public void removeOpportunity(Opportunity opportunity) {
        opportunities.remove(opportunity);
        opportunity.setRiskFormData(null);
    }

    public void addActionPlan(ActionPlan actionPlan) {
        actionPlans.add(actionPlan);
        actionPlan.setRiskFormData(this);
    }

    public void removeActionPlan(ActionPlan actionPlan) {
        actionPlans.remove(actionPlan);
        actionPlan.setRiskFormData(null);
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

    public Integer getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(Integer riskRating) {
        this.riskRating = riskRating;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
