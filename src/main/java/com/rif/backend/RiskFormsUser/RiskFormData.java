package com.rif.backend.RiskFormsUser;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "risk_forms")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    private String status;
    private Integer riskRating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToMany(mappedBy = "riskFormData", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Opportunity> opportunities = new HashSet<>();

    @OneToMany(mappedBy = "riskFormData", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ActionPlan> actionPlans = new HashSet<>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Set<Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(Set<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }

    public Set<ActionPlan> getActionPlans() {
        return actionPlans;
    }

    public void setActionPlans(Set<ActionPlan> actionPlans) {
        this.actionPlans = actionPlans;
    }

}
