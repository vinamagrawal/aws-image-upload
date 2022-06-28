package com.practice.awsimageupload.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserProfile {
    
    private UUID useProfileId;
    private String username;
    private String userProfileImageURL; //S3 Key

}
