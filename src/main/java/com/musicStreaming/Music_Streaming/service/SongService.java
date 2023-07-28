package com.musicStreaming.Music_Streaming.service;

import com.musicStreaming.Music_Streaming.model.Song;
import com.musicStreaming.Music_Streaming.repository.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    ISongRepo songRepo;

    public String addSong(Song song) {
        songRepo.save(song);
        return "Song Added Successfully";
    }

    public String addSongs(List<Song> songs) {
        songRepo.saveAll(songs);
        return "Songs Added Successfully";
    }

    public List<Song> getAllSongs(){
        return songRepo.findAll();
    }

    public Song getSongById(Long songId) {
        return songRepo.findById(songId).orElse(null);
    }

    public String updateSong(Long songId, Song updatedSong) {
        Song mysong = getSongById(songId);
        if(mysong ==null)
            return "Song Id is Invalid";

        mysong.setSongName(updatedSong.getSongName());
        mysong.setSongGenre(updatedSong.getSongGenre());
        mysong.setAlbum(updatedSong.getAlbum());
        mysong.setArtist(updatedSong.getArtist());
        mysong.setSongDurationInSeconds(mysong.getSongDurationInSeconds());

        songRepo.save(mysong);
        return "song updated";
    }

    public String deleteSong(Long songId) {
        Song mysong = getSongById(songId);
        if(mysong ==null)
            return "Song Id is Invalid";

        songRepo.deleteById(songId);
        return "song deleted";
    }
}
