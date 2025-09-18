package Practice.Project1.controller;

import Practice.Project1.Services.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/bucket/{name}")
    public String createBucket(@PathVariable String name) {
        return s3Service.createBucket(name);

    }

    @DeleteMapping("/bucket/{name}")
    public String deleteBucket(@PathVariable String name) {
        return s3Service.deleteBucket(name);

    }

    @PostMapping(path = "/upload/file/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file,
                                             @PathVariable String bucketName) {
        return new ResponseEntity<>(s3Service.uploadFile(file,bucketName), HttpStatus.OK);
    }

    @DeleteMapping("/file/{bucket}/{key}")
    public String deleteFile(@PathVariable String bucket, @PathVariable String key) {
        s3Service.deleteFile(bucket, key);
        return "File deleted: " + key;
    }
}
