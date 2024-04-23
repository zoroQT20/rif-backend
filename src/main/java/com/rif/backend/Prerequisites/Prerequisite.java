package com.rif.backend.Prerequisites;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Prerequisite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unit;

  @OneToMany(mappedBy = "prerequisite", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InternalStakeholder> internalStakeholders;

    @OneToMany(mappedBy = "prerequisite", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ExternalStakeholder> externalStakeholders;

    // Constructors
    public Prerequisite() {
    }

    public Prerequisite(String unit) {
        this.unit = unit;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<InternalStakeholder> getInternalStakeholders() {
        return internalStakeholders;
    }

    public void setInternalStakeholders(List<InternalStakeholder> internalStakeholders) {
        this.internalStakeholders = internalStakeholders;
    }

    public List<ExternalStakeholder> getExternalStakeholders() {
        return externalStakeholders;
    }

    public void setExternalStakeholders(List<ExternalStakeholder> externalStakeholders) {
        this.externalStakeholders = externalStakeholders;
    }
}
