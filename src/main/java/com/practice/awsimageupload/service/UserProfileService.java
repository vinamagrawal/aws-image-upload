package com.practice.awsimageupload.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.practice.awsimageupload.dao.UserProfileDao;
import com.practice.awsimageupload.model.UserProfile;

@Service
public class UserProfileService {
    
    private final UserProfileDao userProfileDao;

    public UserProfileService(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    public List<UserProfile> getUserProfiles() {
        return userProfileDao.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        /*
         * 1. Check if image is not empty
         * 2. Check if file is an image
         * 3. Check if user exists in our database
         * 4. If yes (for all above), then grab some metadata from file if any
         * 5. Store the image in s3 and update database (userProfileImageURL) with s3 image link
         */
    }

}
