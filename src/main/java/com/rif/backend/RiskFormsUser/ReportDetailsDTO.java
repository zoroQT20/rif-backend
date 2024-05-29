package com.rif.backend.RiskFormsUser;

import com.rif.backend.Prerequisites.Prerequisite;

public class ReportDetailsDTO {
    private ReportDTO report;
    private Prerequisite prerequisite;

    public ReportDetailsDTO(Report report, Prerequisite prerequisite) {
        this.report = new ReportDTO(report);
        this.prerequisite = prerequisite;
    }

    public ReportDTO getReport() {
        return report;
    }

    public void setReport(ReportDTO report) {
        this.report = report;
    }

    public Prerequisite getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(Prerequisite prerequisite) {
        this.prerequisite = prerequisite;
    }
}
