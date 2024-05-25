package com.rif.backend.RiskFormsUser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r WHERE r.user.email = :email")
    List<Report> findAllByUserEmail(String email);

    @Query("SELECT r FROM Report r LEFT JOIN FETCH r.riskFormData WHERE r.id = :id")
    Optional<Report> findByIdWithRiskForms(Long id);

    @Query("SELECT r FROM Report r JOIN r.user u JOIN u.prerequisite p WHERE p.unitType = :unitType")
    List<Report> findAllByUnitType(String unitType);

    @Query("SELECT COUNT(r) FROM Report r JOIN r.user u JOIN u.prerequisite p WHERE p.unitType = :unitType")
    long countByUnitType(String unitType);

    @Query("SELECT COUNT(r) FROM Report r JOIN r.user u JOIN u.prerequisite p WHERE p.unitType = :unitType AND r IN (SELECT rf.report FROM RiskFormData rf WHERE rf.submissionDate BETWEEN :startDate AND :endDate)")
    long countByUnitTypeAndDateRange(@Param("unitType") String unitType, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
