package com.practice.awsimageupload.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UserProfile {
    
    private final UUID userProfileId;
    private final String username;
    private String userProfileImageURL; //S3 Key

}
