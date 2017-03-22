package com.ednomy.crm.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Component
public class AwsS3BoxImpl implements AwsS3Box{

	private static final int TIME_OUT = 30000;

	@Autowired
	ApplicationProperties applicationProperties;
	
	Logger LOGGER = LoggerFactory.getLogger(getClass()); 

	@Override
	public PutObjectResult uploadS3(File file) throws AmazonServiceException, AmazonClientException {
		return uploadS3(applicationProperties.getBucketName(), file.getName(), file);
	}
	
	@Override
	public PutObjectResult uploadS3(String s3Path, File file) throws AmazonServiceException, AmazonClientException {
		return uploadS3(applicationProperties.getBucketName(), s3Path, file);
	}
	
	@Override
	public PutObjectResult uploadS3(String bucketName, String s3Path, File file) throws AmazonServiceException, AmazonClientException {
	    BasicAWSCredentials credentials = new BasicAWSCredentials(applicationProperties.getBucketAccessKey(), applicationProperties.getBucketSecretKey());
	    
	    ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setConnectionTimeout(TIME_OUT);
		clientConfiguration.setSocketTimeout(TIME_OUT);
		
	    AmazonS3 s3 = new AmazonS3Client(credentials, clientConfiguration);

	    ObjectMetadata metadata = new ObjectMetadata();

	    metadata.setContentType("image/*");
	    metadata.setContentLength(file.length());
	    
	    InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    PutObjectRequest putRequest = new PutObjectRequest(bucketName, s3Path, input, metadata);

	    // set access to public
	    putRequest.setMetadata(metadata);
	    putRequest.setCannedAcl(CannedAccessControlList.PublicRead);

	    PutObjectResult result = s3.putObject(putRequest);
	    LOGGER.info(result.getETag(), result);
	    return result;
	}
	
	@Override
	public PutObjectResult uploadS3(InputStream inputStream) throws AmazonServiceException, AmazonClientException{
		return uploadS3(applicationProperties.getBucketName(), String.valueOf(System.currentTimeMillis()), inputStream);
	}
	
	@Override
	public PutObjectResult uploadS3(String s3Path, InputStream inputStream) throws AmazonServiceException, AmazonClientException{
		return uploadS3(applicationProperties.getBucketName(), s3Path, inputStream);
	}
	
	@Override
	public PutObjectResult uploadS3(String bucketName, String s3Path, InputStream inputStream) throws AmazonServiceException, AmazonClientException {
	    BasicAWSCredentials credentials = new BasicAWSCredentials(applicationProperties.getBucketAccessKey(), applicationProperties.getBucketSecretKey());
	    
	    ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setConnectionTimeout(TIME_OUT);
		clientConfiguration.setSocketTimeout(TIME_OUT);
		
	    AmazonS3 s3 = new AmazonS3Client(credentials, clientConfiguration);

	    ObjectMetadata metadata = new ObjectMetadata();
	    metadata.setContentType("image/*");

	    try {
			metadata.setContentLength(inputStream.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    PutObjectRequest putRequest = new PutObjectRequest(bucketName, s3Path, inputStream, metadata);

	    // set access to public
	    putRequest.setMetadata(metadata);
	    putRequest.setCannedAcl(CannedAccessControlList.PublicRead);

	    PutObjectResult result = s3.putObject(putRequest);
	    LOGGER.info(result.getETag(), result);	 
	    return result;
	}
	
	@Override
	public PutObjectResult uploadS3(String base64Content) throws AmazonServiceException, AmazonClientException{
		return uploadS3(applicationProperties.getBucketName(), String.valueOf(System.currentTimeMillis()), base64Content);
	}
	
	@Override
	public PutObjectResult uploadS3(String s3Path, String base64Content) throws AmazonServiceException, AmazonClientException{
		return uploadS3(applicationProperties.getBucketName(), s3Path, base64Content);
	}
	
	@Override
	public PutObjectResult uploadS3(String bucketName, String s3Path, String base64Content) throws AmazonServiceException, AmazonClientException {
		
		byte[] decodedBytes = Base64Utils.decode(base64Content.getBytes()); 		
		InputStream inputStream = new ByteArrayInputStream(decodedBytes);
		
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setConnectionTimeout(TIME_OUT);
		clientConfiguration.setSocketTimeout(TIME_OUT);
		
	    BasicAWSCredentials credentials = new BasicAWSCredentials(applicationProperties.getBucketAccessKey(), applicationProperties.getBucketSecretKey());
	    AmazonS3 s3 = new AmazonS3Client(credentials, clientConfiguration);

	    ObjectMetadata metadata = new ObjectMetadata();

	    metadata.setContentType("image/*");
	    try {
			metadata.setContentLength(inputStream.available());
		} catch (IOException e) {
			e.printStackTrace();
		}

	    PutObjectRequest putRequest = new PutObjectRequest(bucketName, s3Path, inputStream, metadata);
	    
	    // set access to public
	    putRequest.setMetadata(metadata);
	    putRequest.setCannedAcl(CannedAccessControlList.PublicRead);

	    PutObjectResult result = s3.putObject(putRequest);
	    LOGGER.info(result.getETag(), result);
	    return result;
	}
	

}
