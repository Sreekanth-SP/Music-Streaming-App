package com.musicStreaming.Music_Streaming.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @NotBlank(message = "Name is Mandatory")
    private String songName;

    @NotBlank(message = "Artist is Mandatory")
    private String artist;

    @NotBlank(message = "Album is Mandatory")
    private String album;

    @NotNull(message = "Duration in seconds is mandatory !!!")
    private Integer songDurationInSeconds;

    @NotBlank(message = "Genre is mandatory !!!")
    private String songGenre;
}
