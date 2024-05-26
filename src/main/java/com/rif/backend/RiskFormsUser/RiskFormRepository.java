package com.rif.backend.RiskFormsUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskFormRepository extends JpaRepository<RiskFormData, Long> {

    @Query(value = "SELECT rf.sda_number as sdaNumber, rf.issue_particulars as issueParticulars, " +
                   "GROUP_CONCAT(rp.description) as riskParticularDescriptions, p.unit as unit, rf.submission_date as submissionDate " +
                   "FROM risk_forms rf " +
                   "JOIN risk_particulars rp ON rf.id = rp.risk_form_data_id " +
                   "JOIN reports r ON rf.report_id = r.id " +
                   "JOIN user u ON r.user_id = u.id " +
                   "JOIN prerequisite p ON u.id = p.user_id " +
                   "GROUP BY rf.sda_number, rf.issue_particulars, p.unit, rf.submission_date", nativeQuery = true)
    List<Object[]> findGroupedBySdaNumber();
}
