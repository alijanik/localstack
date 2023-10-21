package com.alijanik.guid.localstack.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class S3Client {
    private final AmazonS3 s3client;
    private final S3ClientBuilder builder;

    S3Client(final S3ClientBuilder builder, AmazonS3 s3client) {
        this.builder = builder;
        this.s3client = s3client;
    }

    public boolean getObject(String localPath) throws IOException {
        return this.getObject(this.builder.getBucketName(), this.builder.getPath(), localPath);
    }

    public boolean getObject(String bucketName, String s3Path, String localPath) throws IOException {
        if (bucketName == null || bucketName.isBlank() || s3Path == null || s3Path.isBlank()) {
            throw new IOException("bucketName and s3Path can not be null");
        }
        final S3Object s3object = this.s3client.getObject(bucketName, s3Path);
        final S3ObjectInputStream inputStream = s3object.getObjectContent();
        try {
            final File file = new File(localPath);
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Can not save in your local");
        }
        return true;
    }

    public boolean putObject(String localPath) throws IOException {
        return this.putObject(this.builder.getBucketName(), this.builder.getPath(), localPath);
    }

    public boolean putObject(String bucketName, String s3Path, String localPath) throws IOException {
        if (bucketName == null || bucketName.isBlank() || s3Path == null || s3Path.isBlank()) {
            throw new IOException("bucketName and s3Path can not be null");
        }
        this.s3client.putObject(bucketName, s3Path, new File(localPath));
        return true;
    }
}