package com.rif.backend.Prerequisites;

import com.rif.backend.Auth.User;
import com.rif.backend.Auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrerequisiteService {

    private final PrerequisiteRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public PrerequisiteService(PrerequisiteRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Prerequisite savePrerequisite(Prerequisite prerequisite) {
        if (prerequisite.getInternalStakeholders() != null) {
            prerequisite.getInternalStakeholders().forEach(stakeholder -> stakeholder.setPrerequisite(prerequisite));
        }
        if (prerequisite.getExternalStakeholders() != null) {
            prerequisite.getExternalStakeholders().forEach(stakeholder -> stakeholder.setPrerequisite(prerequisite));
        }
        return repository.save(prerequisite);
    }

    @Transactional
    public Prerequisite updatePrerequisite(Prerequisite existing, Prerequisite newPrerequisite) {
        existing.setUnit(newPrerequisite.getUnit());
        existing.setUnitType(newPrerequisite.getUnitType());

        existing.getInternalStakeholders().clear();
        existing.getExternalStakeholders().clear();

        if (newPrerequisite.getInternalStakeholders() != null) {
            newPrerequisite.getInternalStakeholders().forEach(stakeholder -> stakeholder.setPrerequisite(existing));
            existing.getInternalStakeholders().addAll(newPrerequisite.getInternalStakeholders());
        }
        if (newPrerequisite.getExternalStakeholders() != null) {
            newPrerequisite.getExternalStakeholders().forEach(stakeholder -> stakeholder.setPrerequisite(existing));
            existing.getExternalStakeholders().addAll(newPrerequisite.getExternalStakeholders());
        }

        return repository.save(existing);
    }

    public Optional<Prerequisite> getPrerequisiteByUserEmail(String email) {
        return repository.findByUserEmail(email);
    }

    public Prerequisite createOrUpdatePrerequisite(Prerequisite prerequisite, String email) {
        // Check if unit already exists
        if (repository.existsByUnit(prerequisite.getUnit())) {
            throw new IllegalArgumentException("Unit already exists");
        }

        Optional<Prerequisite> existingPrerequisite = getPrerequisiteByUserEmail(email);
        if (existingPrerequisite.isPresent()) {
            return updatePrerequisite(existingPrerequisite.get(), prerequisite);
        } else {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            prerequisite.setUser(user);
            setBackReferences(prerequisite);
            return savePrerequisite(prerequisite);
        }
    }

    private void setBackReferences(Prerequisite prerequisite) {
        if (prerequisite.getInternalStakeholders() != null) {
            prerequisite.getInternalStakeholders().forEach(stakeholder -> stakeholder.setPrerequisite(prerequisite));
        }
        if (prerequisite.getExternalStakeholders() != null) {
            prerequisite.getExternalStakeholders().forEach(stakeholder -> stakeholder.setPrerequisite(prerequisite));
        }
    }

    @Transactional(readOnly = true)
    public long getUnitCountByUnitType(String unitType) {
        return repository.countByUnitType(unitType);
    }

    public Optional<Prerequisite> getPrerequisiteByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Prerequisite> getAllPrerequisites() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<String> getAllUnits() {
        return repository.findAll().stream().map(Prerequisite::getUnit).collect(Collectors.toList());
    }

    public boolean unitExists(String unit) {
        return repository.existsByUnit(unit);
    }
}
