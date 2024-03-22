package com.rif.backend.RiskFormsUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RiskFormData> riskFormData = new ArrayList<>();

    // Constructors, getters, and setters
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

    // Method to add risk form data to report
    public void addRiskFormData(RiskFormData formData) {
        riskFormData.add(formData);
        formData.setReport(this);
    }

    // Method to remove risk form data from report
    public void removeRiskFormData(RiskFormData formData) {
        riskFormData.remove(formData);
        formData.setReport(null);
    }
}
