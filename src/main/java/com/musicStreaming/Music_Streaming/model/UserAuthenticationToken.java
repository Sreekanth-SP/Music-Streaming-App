package com.musicStreaming.Music_Streaming.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTokenId;
    private String userToken;
    private LocalDate userTokenCreationDate;

    @OneToOne
    @JoinColumn(nullable = false , name = "fk_user_id")
    private User user;

    public UserAuthenticationToken(User user) {
        this.user = user;
        this.userTokenCreationDate = LocalDate.now();
        this.userToken = UUID.randomUUID().toString();
    }
}
