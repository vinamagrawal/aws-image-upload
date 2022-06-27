package com.practice.awsimageupload.config;

import java.io.FileReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.opencsv.CSVReader;

@Configuration
public class AWSConfig {

    @Bean
    public AmazonS3 s3() throws Exception {
        AWSCredentials awsCredentials = null;
        CSVReader csvReader = null;
        String[] rootKeyString;
        try {
            csvReader = new CSVReader(new FileReader("~/dev/aws_rootkey.csv"));
            rootKeyString = csvReader.readAll().get(1);
            awsCredentials = new BasicAWSCredentials(rootKeyString[0], rootKeyString[1]);
            return AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
