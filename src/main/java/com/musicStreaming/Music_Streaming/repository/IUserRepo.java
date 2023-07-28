package com.musicStreaming.Music_Streaming.repository;

import com.musicStreaming.Music_Streaming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Long> {

    User findFirstByUserEmail(String newEmail);
}
