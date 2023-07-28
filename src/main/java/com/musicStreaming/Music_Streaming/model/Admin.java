package com.musicStreaming.Music_Streaming.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @NotNull
    private String adminName;
    @NotNull
    private String adminPassword;

    @NotEmpty
    @Size(min=10,max=12)
    @Pattern(regexp = "^[0-9]+$")
    private String adminPhoneNumber;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@admin\\.com$")
    private String adminEmail;
}
