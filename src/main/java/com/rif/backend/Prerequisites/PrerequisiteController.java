package com.rif.backend.Prerequisites;

import com.rif.backend.Prerequisites.Prerequisite;
import com.rif.backend.Prerequisites.PrerequisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prerequisites")
public class PrerequisiteController {

    private final PrerequisiteService service;

    @Autowired
    public PrerequisiteController(PrerequisiteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Prerequisite> createPrerequisite(@RequestBody Prerequisite prerequisite) {
        // Setting back references before saving
        setBackReferences(prerequisite);
        Prerequisite savedPrerequisite = service.savePrerequisite(prerequisite);
        return ResponseEntity.ok(savedPrerequisite);
    }

    @GetMapping
    public ResponseEntity<Iterable<Prerequisite>> getAllPrerequisites() {
        return ResponseEntity.ok(service.listAll());
    }

    // Utility method to set back references
    private void setBackReferences(Prerequisite prerequisite) {
        if (prerequisite.getInternalStakeholders() != null) {
            prerequisite.getInternalStakeholders().forEach(stakeholder -> stakeholder.setPrerequisite(prerequisite));
        }
        if (prerequisite.getExternalStakeholders() != null) {
            prerequisite.getExternalStakeholders().forEach(stakeholder -> stakeholder.setPrerequisite(prerequisite));
        }
    }
}
