package com.demo.demospringdatajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class JpaBaseEntity {
    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist(){
        LocalDateTime nowDateTime = LocalDateTime.now();
        createdDate=nowDateTime;
        updateDate=nowDateTime;
    }

    @PreUpdate
    public void preUpdate(){
        LocalDateTime nowDateTime = LocalDateTime.now();
        updateDate=nowDateTime;
    }
}
