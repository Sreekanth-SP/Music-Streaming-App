package com.musicStreaming.Music_Streaming.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class AdminAuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminTokenId;
    private String adminToken;
    private LocalDate adminTokenCreationDate;

    @OneToOne
    @JoinColumn(nullable = false, name = "fk_admin_id")
    private Admin admin;

    public AdminAuthenticationToken(Admin admin) {
        this.admin = admin;
        this.adminTokenCreationDate = LocalDate.now();
        this.adminToken = UUID.randomUUID().toString();
    }
}