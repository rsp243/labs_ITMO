package backend.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Data
public class S3Worker {

    private final MinioClient minioClient;
    private final String bucketName = "my-bucket";
    private final ImportLogger importLogger;

    @Transactional
    public void uploadLogFile(String objectHash) throws InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, IOException, MinioException {
        log.info("Uploading import file");
        try {
            log.info("Try-catch block");
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("Minio bucket created");
            }
            File logFile = importLogger.getLogFile();
            log.info(logFile.getName());
            try (InputStream inputStream = new FileInputStream(logFile)) {
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectHash)
                        .stream(inputStream, logFile.length(), -1)
                        .build());
                log.info("Log file uploaded successfully: " + logFile.getName());
            }
        } catch (MinioException e) {
            throw new MinioException("Error occurred while uploading log file to S3: " + e.toString());
        }
    }

    public String generatePresignedUrl(String objectName) throws Exception {
        String url = minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(2, TimeUnit.HOURS)
                .build());

        return url;
    }
}
