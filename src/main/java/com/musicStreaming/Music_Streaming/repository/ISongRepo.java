package com.musicStreaming.Music_Streaming.repository;

import com.musicStreaming.Music_Streaming.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepo extends JpaRepository<Song,Long> {
}
