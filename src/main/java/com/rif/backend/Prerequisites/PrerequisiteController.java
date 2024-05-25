package com.rif.backend.Prerequisites;

import com.rif.backend.Auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/prerequisites")
public class PrerequisiteController {

    private final PrerequisiteService service;

    @Autowired
    public PrerequisiteController(PrerequisiteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Prerequisite> createOrUpdatePrerequisite(@RequestBody Prerequisite prerequisite, Principal principal) {
        String email = principal.getName();
        Prerequisite savedPrerequisite = service.createOrUpdatePrerequisite(prerequisite, email);
        return ResponseEntity.ok(savedPrerequisite);
    }

    @GetMapping
    public ResponseEntity<Prerequisite> getPrerequisite(Principal principal) {
        Optional<Prerequisite> prerequisite = service.getPrerequisiteByUserEmail(principal.getName());
        if (prerequisite.isPresent()) {
            return ResponseEntity.ok(prerequisite.get());
        } else {
            Prerequisite emptyPrerequisite = new Prerequisite();
            emptyPrerequisite.setInternalStakeholders(new ArrayList<>());
            emptyPrerequisite.setExternalStakeholders(new ArrayList<>());
            return ResponseEntity.ok(emptyPrerequisite);
        }
    }
}
