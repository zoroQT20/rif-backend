package com.rif.backend.Prerequisites;

import com.rif.backend.Prerequisites.Prerequisite;
import com.rif.backend.Prerequisites.PrerequisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrerequisiteService {

    private final PrerequisiteRepository repository;

    @Autowired
    public PrerequisiteService(PrerequisiteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Prerequisite savePrerequisite(Prerequisite prerequisite) {
        if (prerequisite.getInternalStakeholders() != null) {
            prerequisite.getInternalStakeholders().forEach(stakeholder -> {
                stakeholder.setPrerequisite(prerequisite);
            });
        }
        if (prerequisite.getExternalStakeholders() != null) {
            prerequisite.getExternalStakeholders().forEach(stakeholder -> {
                stakeholder.setPrerequisite(prerequisite);
            });
        }
        return repository.save(prerequisite);
    }

    public List<Prerequisite> listAll() {
        return repository.findAll();
    }
}
