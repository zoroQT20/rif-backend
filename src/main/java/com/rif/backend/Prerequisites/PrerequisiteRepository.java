package com.rif.backend.Prerequisites;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {
    Optional<Prerequisite> findByUserEmail(String email);
}