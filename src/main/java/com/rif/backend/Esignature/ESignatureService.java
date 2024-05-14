package com.rif.backend.Esignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ESignatureService {
    @Autowired
    private ESignatureRepository repository;

    @Transactional
    public ESignature saveESignature(ESignature eSignature) {
        return repository.save(eSignature);  // This will save the ESignature object to the database
    }

    @Transactional(readOnly = true)
    public ESignature findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
