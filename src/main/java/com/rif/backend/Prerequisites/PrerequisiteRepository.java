package com.rif.backend.Prerequisites;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {
    // You can add custom query methods here if needed.
}