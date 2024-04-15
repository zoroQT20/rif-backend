package com.rif.backend.RiskFormsUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // Fetch only the primary relationships in the first go
    @Query("SELECT r FROM Report r JOIN FETCH r.riskFormData WHERE r.id = :id")
    Optional<Report> findByIdWithRiskForms(Long id);
}
