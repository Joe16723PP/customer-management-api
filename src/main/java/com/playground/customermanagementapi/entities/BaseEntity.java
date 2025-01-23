package com.playground.customermanagementapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Column(name = "modified_on", nullable = false)
    private long modifiedOn;

    @Column(name = "created_on", nullable = false, updatable = false)
    private long createdOn;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "modified_by", nullable = false)
    private String modifiedBy;

    @PrePersist
    protected void onCreate() {
        this.createdOn = System.currentTimeMillis();
        this.modifiedOn = this.createdOn;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedOn = System.currentTimeMillis(); // Update modified timestamp
    }
}
