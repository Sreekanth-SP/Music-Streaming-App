package com.musicStreaming.Music_Streaming.service;

import com.musicStreaming.Music_Streaming.model.Playlist;
import com.musicStreaming.Music_Streaming.repository.IPlaylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    IPlaylistRepo playlistRepo;

    public String createPlaylist(Playlist playlist) {
        playlistRepo.save(playlist);
        return "Created Playlist";
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepo.findAll();
    }

    public Playlist getPlaylistById(Long id) {
        return playlistRepo.findById(id).orElse(null);
    }

    public String updatePlaylist(Long id, String newName) {
        Playlist playlist = getPlaylistById(id);
        if(playlist == null)
            return "Id is Invalid";
        playlist.setName(newName);

        playlistRepo.save(playlist);
        return "PlayList Name is Updated";
    }

    public String deletePlaylistById(Long id) {
        Playlist playlist = getPlaylistById(id);
        if(playlist == null)
            return "Id is Invalid";


        playlistRepo.deleteById(id);
        return "PlayList is deleted";
    }
}
