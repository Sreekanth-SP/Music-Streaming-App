package com.musicStreaming.Music_Streaming.repository;

import com.musicStreaming.Music_Streaming.model.Admin;
import com.musicStreaming.Music_Streaming.model.AdminAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminAuthenticationRepo extends JpaRepository<AdminAuthenticationToken,Long> {
    AdminAuthenticationToken findFirstByAdminToken(String authTokenValue);

    AdminAuthenticationToken findFirstByAdmin(Admin admin);
}
