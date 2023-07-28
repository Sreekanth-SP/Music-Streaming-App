package com.musicStreaming.Music_Streaming.service;

import com.musicStreaming.Music_Streaming.model.Admin;
import com.musicStreaming.Music_Streaming.model.AdminAuthenticationToken;
import com.musicStreaming.Music_Streaming.repository.IAdminAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticationService {
    @Autowired
    IAdminAuthenticationRepo adminAuthenticationRepo;

    public boolean authenticate(String email, String authTokenValue){
        AdminAuthenticationToken authToken = adminAuthenticationRepo.findFirstByAdminToken(authTokenValue);

        if(authToken == null){
            return false;
        }

        String tokenConnectedEmail = authToken.getAdmin().getAdminEmail();
        return tokenConnectedEmail.equals(email);
    }

    public void saveAuthToken(AdminAuthenticationToken authToken) {
        adminAuthenticationRepo.save(authToken);
    }

    public AdminAuthenticationToken findFirstByAdmin(Admin admin) {
        return adminAuthenticationRepo.findFirstByAdmin(admin);
    }

    public void removeToken(AdminAuthenticationToken token) {
        adminAuthenticationRepo.delete(token);
    }

}
