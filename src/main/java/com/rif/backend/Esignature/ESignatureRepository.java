package com.rif.backend.Esignature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESignatureRepository extends JpaRepository<ESignature, Long> {
}
