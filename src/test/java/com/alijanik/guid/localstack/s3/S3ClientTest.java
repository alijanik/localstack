package com.alijanik.guid.localstack.s3;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class S3ClientTest {

    private static final String localFileName = "README.md";

    private static S3Client getClient() {
        return new S3ClientBuilder()
                .withEndPoint("http://localhost:4566", "us-east-1")
                .withBucketName("document").withPath("README.md")
                .build();
    }

    @Test
    public void testAPut() throws IOException {
        final S3Client client = getClient();
        Assert.assertTrue(client.putObject(localFileName));
    }

    @Test
    public void testBGet() throws IOException {
        final S3Client client = getClient();
        Assert.assertTrue(client.getObject(localFileName));
    }
}