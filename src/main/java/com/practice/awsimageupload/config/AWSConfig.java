package com.practice.awsimageupload.config;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.opencsv.CSVReader;

@Configuration
public class AWSConfig {

    private final String FILE_LOCATION = "/Users/vinamagrawal/dev/aws_rootkey.csv";

    @Bean
    public AmazonS3 s3() throws Exception {
        AWSCredentials awsCredentials = null;
        CSVReader csvReader = null;
        List<String> rootKeyString = new ArrayList<>();
        try {
            csvReader = new CSVReader(new FileReader(FILE_LOCATION));
            rootKeyString.add(csvReader.readNext()[0].split("=")[1]);
            rootKeyString.add(csvReader.readNext()[0].split("=")[1]);
            awsCredentials = new BasicAWSCredentials(rootKeyString.get(0), rootKeyString.get(1));
            return AmazonS3ClientBuilder
                    .standard()
                    .withRegion(Regions.AP_SOUTH_1)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
