package org.example.book;

import org.example.book.dto.req.ReqRegisterBook;
import org.example.entity.BookEntity;
import org.example.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    @Value("${s3.bucket}")
    private String bucketName;

    @Value("${s3.endpoint}")
    private String endpoint;

    @Value("${s3.access-key}")
    private String accessKey;

    @Value("${s3.secret-key}")
    private String secretKey;

    @Value("${s3.public-url}")
    private String publicUrl;

    private S3Client s3Client;

    public BookService() {
    }

    @PostConstruct
    public void init() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        
        S3Configuration s3Config = S3Configuration.builder()
            .pathStyleAccessEnabled(true)
            .build();
            
        this.s3Client = S3Client.builder()
            .region(Region.AP_NORTHEAST_2)
            .endpointOverride(URI.create(endpoint))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .serviceConfiguration(s3Config)
            .build();
    }

    public List<BookEntity> getBooks(long userId) {
        return bookRepo.findAllByUserIdAndDeletedAtIsNullOrderByIdDesc(userId);
    }

    public BookEntity getBook(long id, long userId) {
        BookEntity book = bookRepo.findByIdAndUserIdAndDeletedAtIsNull(id, userId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found book");
        }

        return book;
    }

    public void registerBook(ReqRegisterBook payload, MultipartFile coverImage, long userId) {
        String coverImageUrl = uploadImageAndGetUrl(coverImage, null);
        BookEntity createBookEntity = payload.toCreateBookEntity(userId, coverImageUrl);
        bookRepo.save(createBookEntity);
    }

    public void modifyBook(long id, ReqRegisterBook payload, MultipartFile coverImage, long userId) {
        BookEntity book = bookRepo.findByIdAndUserIdAndDeletedAtIsNull(id, userId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found book");
        }

        String coverImageUrl = uploadImageAndGetUrl(coverImage, book.getCoverImageUrl());
        BookEntity modifyBookEntity = payload.toCreateBookEntity(userId, coverImageUrl);
        bookRepo.updateById(id, modifyBookEntity);
    }

    private String uploadImageAndGetUrl(MultipartFile coverImage, String coverImageUrl) {
        String processedCoverImageUrl = coverImageUrl;

        if (coverImage != null && !coverImage.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + coverImage.getOriginalFilename();
                s3Client.putObject(
                    PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .contentType(coverImage.getContentType())
                        .build(),
                    RequestBody.fromInputStream(coverImage.getInputStream(), coverImage.getSize())
                );
                processedCoverImageUrl = fileName;
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image");
            }
        }

        return publicUrl.concat(processedCoverImageUrl);
    }
}