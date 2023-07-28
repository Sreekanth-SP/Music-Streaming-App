package com.musicStreaming.Music_Streaming.repository;

import com.musicStreaming.Music_Streaming.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,Long> {
    Admin findFirstByAdminEmail(String newEmail);
}
