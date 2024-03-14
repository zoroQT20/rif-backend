package com.rif.backend.RiskFormsUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskIdentificationFormRepository extends JpaRepository<RiskIdentificationFormEntity, Long> {
    // You can add custom query methods here if needed
}