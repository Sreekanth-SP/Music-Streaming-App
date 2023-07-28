package com.musicStreaming.Music_Streaming.service;

import com.musicStreaming.Music_Streaming.model.User;
import com.musicStreaming.Music_Streaming.model.UserAuthenticationToken;
import com.musicStreaming.Music_Streaming.repository.IUserAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    @Autowired
    IUserAuthenticationRepo userAuthenticationRepo;

    public boolean authenticate(String email, String authTokenValue){
        UserAuthenticationToken authToken = userAuthenticationRepo.findFirstByUserToken(authTokenValue);

        if(authToken == null){
            return false;
        }

        String tokenConnectedEmail = authToken.getUser().getUserEmail();
        return tokenConnectedEmail.equals(email);
    }

    public void saveAuthToken(UserAuthenticationToken authToken) {
        userAuthenticationRepo.save(authToken);
    }

    public UserAuthenticationToken findFirstByUser(User user) {
        return userAuthenticationRepo.findFirstByUser(user);
    }

    public void removeToken(UserAuthenticationToken token) {
        userAuthenticationRepo.delete(token);
    }
}
