package com.umart.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "admin_actions_admins")
@Data
public class AdminActionsAdmin {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String action;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "super_admin_id", nullable = false)
    private Admin superAdmin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;
}
