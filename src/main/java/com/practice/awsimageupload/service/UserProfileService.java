package com.practice.awsimageupload.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.practice.awsimageupload.bucket.BucketName;
import com.practice.awsimageupload.dao.UserProfileDao;
import com.practice.awsimageupload.filestore.FileStore;
import com.practice.awsimageupload.model.UserProfile;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileDao userProfileDao;
    private final FileStore fileStore;

    public UserProfileService(UserProfileDao userProfileDao, FileStore fileStore) {
        this.userProfileDao = userProfileDao;
        this.fileStore = fileStore;
    }

    public List<UserProfile> getUserProfiles() {
        return userProfileDao.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // 1. Check if image is not empty
        isFileEmpty(file);

        // 2. Check if file is an image
        isFileAnImage(file);

        // 3. Check if user exists in our database
        UserProfile user = getUserProfileOrThrowException(userProfileId);

        // 4. If yes (for all above), then grab some metadata from file if any
        Map<String, String> metaData = extractMetaData(file);

        // 5. Store the image in s3 and update database (userProfileImageURL) with s3
        // image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.saveFile(path, filename, Optional.of(metaData), file.getInputStream());
            user.setUserProfileImageURL(filename);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));
        return metaData;
    }

    private UserProfile getUserProfileOrThrowException(UUID userProfileId) {
        return userProfileDao
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }

    private void isFileAnImage(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_PNG.getMimeType()
            ).contains(file.getContentType())
        ) {
            throw new IllegalStateException("File must be an image - " + file.getContentType());
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]");
        }
    }

}
