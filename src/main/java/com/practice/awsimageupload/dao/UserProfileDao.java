package com.practice.awsimageupload.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.practice.awsimageupload.model.UserProfile;

@Repository
public class UserProfileDao {

    private final MockUserProfileDataStore mockUserProfileDataStore;

    public UserProfileDao(MockUserProfileDataStore mockUserProfileDataStore) {
        this.mockUserProfileDataStore = mockUserProfileDataStore;
    }
    
    public List<UserProfile> getUserProfiles() {
        return mockUserProfileDataStore.getUserProfiles();
    }

}
