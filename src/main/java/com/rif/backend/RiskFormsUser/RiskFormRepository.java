    package com.rif.backend.RiskFormsUser;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    
    @Repository
    public interface RiskFormRepository extends JpaRepository<RiskFormData, Long> {
        // Custom query methods if needed
    }
    