package com.musicStreaming.Music_Streaming.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String username;
    @NotNull
    private String userPassword;

    @NotEmpty
    @Email
    private String userEmail;

    @NotEmpty
    @Size(min=10,max=12)
    @Pattern(regexp = "^[0-9]+$")
    private String userContactNumber;
}
