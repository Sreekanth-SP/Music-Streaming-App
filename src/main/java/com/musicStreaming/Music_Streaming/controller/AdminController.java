package com.musicStreaming.Music_Streaming.controller;


import com.musicStreaming.Music_Streaming.model.Admin;
import com.musicStreaming.Music_Streaming.model.Song;
import com.musicStreaming.Music_Streaming.model.dto.SignInInput;
import com.musicStreaming.Music_Streaming.model.dto.SignUpOutput;


import com.musicStreaming.Music_Streaming.service.AdminAuthenticationService;
import com.musicStreaming.Music_Streaming.service.AdminService;
import com.musicStreaming.Music_Streaming.service.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    AdminAuthenticationService adminAuthenticationService;

    @Autowired
    SongService songService;


    // sign up, sign in , sign out for music-streaming admin
    @PostMapping("admin/signup")
    public SignUpOutput signUpAppAdmin(@RequestBody Admin admin){

        return adminService.signUpAdmin(admin);
    }

    @PostMapping("admin/signIn")
    public String sigInAppAdmin(@RequestBody @Valid SignInInput signInInput) {
        return adminService.signInAdmin(signInInput);
    }

    @DeleteMapping("admin/signOut")
    public String sigOutAppAdmin(String email, String token) {
        if(adminAuthenticationService.authenticate(email,token)) {
            return adminService.signOutAdmin(email);
         }
        else {
            return "Sign out not allowed for non authenticated admin.";
        }
    }

    // Admin-related CRUD operations and endpoints

    @GetMapping("admins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("admin/{adminId}")
    public Admin getAdminById(@PathVariable Long adminId) {
        return adminService.getAdminById(adminId);
    }

    @PutMapping("admin/{adminId}")
    public String updateAdminById(@PathVariable Long adminId, @Valid @RequestBody Admin updatedAdmin) {
        return adminService.updateAdminById(adminId, updatedAdmin);
    }


    @PostMapping("admin/song")
    public String addSong(String email ,String token, @Valid @RequestBody Song song) {
        if(adminAuthenticationService.authenticate(email,token)) {
            return songService.addSong(song);
        }

        return "Creating song for non authenticated admin is not possible.";
    }

    @PostMapping("admin/songs")
    public String addSongs(String email ,String token, @Valid @RequestBody List<Song> songs) {
        if(adminAuthenticationService.authenticate(email,token)) {
            return songService.addSongs(songs);
        }

        return "Creating song for non authenticated admin is not possible.";
    }

    @GetMapping("admin/songs")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("admin/song/{songId}")
    public Song getSongById(@PathVariable Long songId) {
        return songService.getSongById(songId);
    }

    @PutMapping("admin/song/{songId}")
    public String updateSong(@PathVariable Long songId, @Valid @RequestBody Song updatedSong) {
        return songService.updateSong(songId, updatedSong);
    }

    @DeleteMapping("admin/song/{songId}")
    public String deleteSong(@PathVariable Long songId) {
        return songService.deleteSong(songId);
    }
}
