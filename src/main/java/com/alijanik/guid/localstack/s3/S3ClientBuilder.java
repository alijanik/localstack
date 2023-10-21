package com.alijanik.guid.localstack.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3ClientBuilder {
    final AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();

    private boolean containerCredentials = false;
    private String region;
    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String path;

    public boolean isContainerCredentials() {
        return containerCredentials;
    }

    public void setContainerCredentials(boolean containerCredentials) {
        this.containerCredentials = containerCredentials;
    }

    public final S3ClientBuilder withContainerCredentials(boolean containerCredentials) {
        this.setContainerCredentials(containerCredentials);
        return this;
    }

    public final String getRegion() {
        return region;
    }

    public final void setRegion(String region) {
        this.region = region;
    }

    public final S3ClientBuilder withRegion(String region) {
        this.setRegion(region);
        this.builder.withRegion(region);
        return this;
    }

    public final String getEndPoint() {
        return endPoint;
    }

    public final void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public final S3ClientBuilder withEndPoint(String endPoint, String region) {
        this.setEndPoint(endPoint);
        this.setRegion(region);
        this.builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, region));
        return this;
    }

    public final String getAccessKey() {
        return accessKey;
    }

    public final void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public final String getSecretKey() {
        return secretKey;
    }

    public final void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public final S3ClientBuilder withCredentials(String accessKey, String secretKey) {
        this.setAccessKey(accessKey);
        this.setSecretKey(secretKey);
        final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.builder.withCredentials(new AWSStaticCredentialsProvider(credentials));
        return this;
    }

    public final String getBucketName() {
        return bucketName;
    }

    public final void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public final S3ClientBuilder withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public final S3ClientBuilder withPath(String path) {
        this.setPath(path);
        return this;
    }

    public final S3Client build() {
        final AmazonS3 s3client;
        if (this.isContainerCredentials()) {
            s3client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new DefaultAWSCredentialsProviderChain())
                    .build();
        } else {
            s3client = builder.withPathStyleAccessEnabled(true)
                    .build();
        }
        return new S3Client(this, s3client);
    }
}