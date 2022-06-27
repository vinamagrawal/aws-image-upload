package com.practice.awsimageupload.bucket;

public enum BucketName {
    
    PROFILE_IMAGE("vinamagrawal-aws-image-upload");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

}
