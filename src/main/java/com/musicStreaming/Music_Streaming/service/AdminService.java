package com.musicStreaming.Music_Streaming.service;

import com.musicStreaming.Music_Streaming.model.Admin;
import com.musicStreaming.Music_Streaming.model.AdminAuthenticationToken;
import com.musicStreaming.Music_Streaming.model.dto.SignInInput;
import com.musicStreaming.Music_Streaming.model.dto.SignUpOutput;
import com.musicStreaming.Music_Streaming.repository.IAdminRepo;
import com.musicStreaming.Music_Streaming.service.utility.EmailHandler;
import com.musicStreaming.Music_Streaming.service.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    AdminAuthenticationService adminAuthenticationService;

    public SignUpOutput signUpAdmin(Admin admin) {
        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = admin.getAdminEmail();

        if(newEmail == null){
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this admin email already exists ??
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(newEmail);

        if(existingAdmin != null) {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(admin.getAdminPassword());

            //save the admin with the new encrypted password

            admin.setAdminPassword(encryptedPassword);
            adminRepo.save(admin);

            return new SignUpOutput(signUpStatus, "Admin registered successfully!!!");
        }
        catch(Exception e) {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String signInAdmin(SignInInput signInInput) {
        String signInStatusMessage = null;
        String signInEmail = signInInput.getEmail();

        if(signInEmail == null){
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this admin email already exists ??
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(signInEmail);

        if(existingAdmin == null){
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingAdmin.getAdminPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and  admin id is valid
                AdminAuthenticationToken authToken  = new AdminAuthenticationToken(existingAdmin);
                adminAuthenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail("sreekanthharisree2000@gmail.com","email testing",authToken.getAdminToken());
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

    public String signOutAdmin(String email) {

        Admin admin = adminRepo.findFirstByAdminEmail(email);
        AdminAuthenticationToken token = adminAuthenticationService.findFirstByAdmin(admin);
        adminAuthenticationService.removeToken(token);
        return "Admin Signed out successfully";
    }

    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    public Admin getAdminById(Long adminId) {
        return adminRepo.findById(adminId).orElse(null);
    }

    public String updateAdminById(Long adminId, Admin updatedAdmin) {
        Admin myAdmin = getAdminById(adminId);
        if(myAdmin ==null){
            return "Admin Id: " +adminId+" Not Found!!!";
        }
        myAdmin.setAdminEmail(updatedAdmin.getAdminEmail());
        myAdmin.setAdminName(updatedAdmin.getAdminName());
        myAdmin.setAdminPassword(updatedAdmin.getAdminPassword());
        myAdmin.setAdminPhoneNumber(updatedAdmin.getAdminPhoneNumber());

        adminRepo.save(myAdmin);
        return "Updated Successfully";
    }
}
