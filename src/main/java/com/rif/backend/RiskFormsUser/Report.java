package com.rif.backend.RiskFormsUser;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.rif.backend.Auth.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reports")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RiskFormData> riskFormData = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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
}
