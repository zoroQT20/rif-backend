package com.rif.backend.RiskFormsUser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r WHERE r.user.email = :email")
    List<Report> findAllByUserEmail(String email);

    @Query("SELECT r FROM Report r LEFT JOIN FETCH r.riskFormData WHERE r.id = :id")
    Optional<Report> findByIdWithRiskForms(Long id);
}
