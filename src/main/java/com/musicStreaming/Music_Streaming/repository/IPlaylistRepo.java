package com.musicStreaming.Music_Streaming.repository;

import com.musicStreaming.Music_Streaming.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaylistRepo extends JpaRepository<Playlist,Long> {
}
