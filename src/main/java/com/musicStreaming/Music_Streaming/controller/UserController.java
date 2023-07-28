package com.musicStreaming.Music_Streaming.controller;

import com.musicStreaming.Music_Streaming.model.Playlist;
import com.musicStreaming.Music_Streaming.model.User;
import com.musicStreaming.Music_Streaming.model.dto.SignInInput;
import com.musicStreaming.Music_Streaming.model.dto.SignUpOutput;
import com.musicStreaming.Music_Streaming.service.PlaylistService;
import com.musicStreaming.Music_Streaming.service.UserAuthenticationService;
import com.musicStreaming.Music_Streaming.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    PlaylistService playlistService;


    // sign up, sign in , sign out a particular music-streaming user
    @PostMapping("user/signup")
    public SignUpOutput signUpAppUser(@RequestBody User user)
    {

        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String sigInAppUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String sigOutAppUser(String email, String token)
    {
        if(userAuthenticationService.authenticate(email,token)) {
            return userService.signOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }
    }
    @GetMapping("user/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("user/{userId}")
    public String updateUser(@PathVariable Long userId, @Valid @RequestBody User user) {
        return userService.updateUser(userId, user);
    }


    // Add song to user playlist
    @PostMapping("user/playlist")
    public String addPlaylist(@RequestBody Playlist playlist){
        return playlistService.createPlaylist(playlist);
    }

    @GetMapping("user/playlists")
    public List<Playlist> getAllPlaylists(){
        return playlistService.getAllPlaylists();
    }

    @GetMapping("user/playlist/{id}")
    public Playlist getPlaylistById(@PathVariable Long id){
        return  playlistService.getPlaylistById(id);
    }

    @PutMapping("user/playlist/{id}/name/{newName}")
    public String updatePlaylistByName(@PathVariable Long id,@PathVariable String newName){
        return playlistService.updatePlaylist(id,newName);
    }

    @DeleteMapping("user/playlist/{id}")
    public String deletePlaylistById(@PathVariable Long id){
        return playlistService.deletePlaylistById(id);
    }
}
