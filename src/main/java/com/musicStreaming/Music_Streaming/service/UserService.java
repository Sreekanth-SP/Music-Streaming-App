package com.musicStreaming.Music_Streaming.service;

import com.musicStreaming.Music_Streaming.model.User;
import com.musicStreaming.Music_Streaming.model.UserAuthenticationToken;
import com.musicStreaming.Music_Streaming.model.dto.SignInInput;
import com.musicStreaming.Music_Streaming.model.dto.SignUpOutput;
import com.musicStreaming.Music_Streaming.repository.IUserRepo;
import com.musicStreaming.Music_Streaming.service.utility.EmailHandler;
import com.musicStreaming.Music_Streaming.service.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    public SignUpOutput signUpUser(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

            //save the user with the new encrypted password

            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }


    public String signInUser(SignInInput signInInput) {


        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if(existingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                UserAuthenticationToken authToken  = new UserAuthenticationToken(existingUser);
                userAuthenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail("sreekanthharisree2000@gmail.com","email testing",authToken.getUserToken());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }


    public String signOutUser(String email) {

        User user = userRepo.findFirstByUserEmail(email);
        UserAuthenticationToken token = userAuthenticationService.findFirstByUser(user);
        userAuthenticationService.removeToken(token);
        return "User Signed out successfully";
    }

    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public String updateUser(Long userId, User user) {
        User myUser = getUserById(userId);
        if(myUser == null)
            return "Invalid Id";

        myUser.setUsername(user.getUsername());
        myUser.setUserEmail(user.getUserEmail());
        myUser.setUserPassword(user.getUserPassword());
        myUser.setUserContactNumber(user.getUserContactNumber());

        userRepo.save(myUser);

        return "Updated ";
    }
}
