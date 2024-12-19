package backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MinioConfig {

    private final String minioUrl = "http://172.18.0.2:9000";
    private final String accessKey = "user";
    private final String secretKey = "secret_key";

    @Bean
    public MinioClient minioClient() {
        log.info("creating minio connection");
        return MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(accessKey, secretKey)
                .build();
    }
}
