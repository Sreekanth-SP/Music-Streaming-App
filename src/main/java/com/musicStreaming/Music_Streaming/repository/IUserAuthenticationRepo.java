package com.musicStreaming.Music_Streaming.repository;

import com.musicStreaming.Music_Streaming.model.User;
import com.musicStreaming.Music_Streaming.model.UserAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserAuthenticationRepo extends JpaRepository<UserAuthenticationToken,Long> {
    UserAuthenticationToken findFirstByUser(User user);

    UserAuthenticationToken findFirstByUserToken(String authTokenValue);
}
