package com.practice.awsimageupload.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.practice.awsimageupload.model.UserProfile;

@Repository
public class MockUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("d2604189-6d4a-4116-8cba-022b14ce0cf5"), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("13825ace-c81d-4e96-840f-7d6c6ccc8a39"), "antoniojunior", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
