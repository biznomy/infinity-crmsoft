package com.ednomy.crm.util;

import java.io.File;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.PutObjectResult;

public interface AwsS3Box {

	PutObjectResult uploadS3(File file) throws AmazonServiceException,
			AmazonClientException;

	PutObjectResult uploadS3(String s3Path, File file)
			throws AmazonServiceException, AmazonClientException;

	PutObjectResult uploadS3(String bucketName, String s3Path, File file)
			throws AmazonServiceException, AmazonClientException;

	PutObjectResult uploadS3(InputStream inputStream)
			throws AmazonServiceException, AmazonClientException;

	PutObjectResult uploadS3(String s3Path, InputStream inputStream)
			throws AmazonServiceException, AmazonClientException;

	PutObjectResult uploadS3(String bucketName, String s3Path,
			InputStream inputStream) throws AmazonServiceException,
			AmazonClientException;

	PutObjectResult uploadS3(String base64Content)
			throws AmazonServiceException, AmazonClientException;

	PutObjectResult uploadS3(String s3Path, String base64Content)
			throws AmazonServiceException, AmazonClientException;

	PutObjectResult uploadS3(String bucketName, String s3Path,
			String base64Content) throws AmazonServiceException,
			AmazonClientException;

}
