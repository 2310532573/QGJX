package com.luolin.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class AwsConfiguration {
    //亚马逊云存储key
    //@Value("${aws.accessKey}")
    private String accessKey;
    //@Value("${aws.secretKey}")
    private String secretKey;


//    @Bean
//    public AmazonS3 s3Client(){
//        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//        return AmazonS3ClientBuilder.standard()
//                .withRegion(Regions.AP_SOUTHEAST_1)
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .build();
//    }
//
//    @Bean
//    public TransferManager transferManager(AmazonS3 s3Client){
//        return  TransferManagerBuilder.standard()
//                .withS3Client(s3Client)
//                .build();
//    }
}
