package Practice.Project1.Services;

import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Service
@Slf4j
public class S3Service {

    private final S3Client s3Client;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    private boolean doesBucketExist(String bucketName){
        try {
            HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build();
            s3Client.headBucket(headBucketRequest);
            return true;
        } catch (S3Exception e) {
            if (e.statusCode() == 404) {
                return false;
            }
           return true;
        }
    }

    public String createBucket(String bucketName) {
        if(!doesBucketExist(bucketName)){
            try {
                s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
                return "Bucket created SuccessFully";
            }catch (Exception e){
                return e.getMessage();
            }
        }
        else{
            return "Bucket Already exist";
        }
    }

    public String deleteBucket(String bucketName) {
        if(doesBucketExist(bucketName)){
            try {
                s3Client.deleteBucket(DeleteBucketRequest.builder().bucket(bucketName).build());
                return "bucket deleted";
            }catch (Exception e){
                return "Bucket Not Empty Cannot be deleted";
            }
        }
        else{
            return "bucket does not exist";
        }
    }

    public String uploadFile(MultipartFile file, String bucketName) {
        try {
            String key = file.getOriginalFilename();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest,
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));

            return "File uploaded successfully: " + key;

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public String deleteFile(String bucketName, String key) {
        DeleteObjectResponse deleteObjectResponse = s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build());
        return deleteObjectResponse.toString();

    }
}
