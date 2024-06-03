package com.rif.backend.RiskFormsUser;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.rif.backend.Auth.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reports")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Report {

    public enum ReportStatus {
        APPROVER_PENDING,
        APPROVER_APPROVED,
        APPROVER_FOR_REVISION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RiskFormData> riskFormData = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status = ReportStatus.APPROVER_PENDING;

    @Column(name = "approver_comment", length = 1000)
    private String approverComment;

    public Report() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RiskFormData> getRiskFormData() {
        return riskFormData;
    }

    public void setRiskFormData(List<RiskFormData> riskFormData) {
        this.riskFormData = riskFormData;
    }

    public void addRiskFormData(RiskFormData formData) {
        riskFormData.add(formData);
        formData.setReport(this);
    }

    public void removeRiskFormData(RiskFormData formData) {
        riskFormData.remove(formData);
        formData.setReport(null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getApproverComment() {
        return approverComment;
    }

    public void setApproverComment(String approverComment) {
        this.approverComment = approverComment;
    }
}
