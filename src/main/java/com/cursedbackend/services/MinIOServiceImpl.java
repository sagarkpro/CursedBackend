package com.cursedbackend.services;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cursedbackend.constants.FileUpload;
import com.cursedbackend.constants.MinIO;
import com.cursedbackend.dtos.ResponseDto;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class MinIOServiceImpl implements MinIOService {
    private final S3Client client;
    @Value("${minio.bucket}")
    private String bucket;

    @Value("${minio.public-endpoint}")
    private String minIOEndpoint;

    public MinIOServiceImpl(S3Client client) {
        this.client = client;
    }

    @Override
    public ResponseDto<String> upload(MultipartFile file, String folder) {

        String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())
                + "-"
                + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10)
                + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        String key = folder + "/" + fileName;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType())
                .build();

        try {
            client.putObject(
                    request,
                    RequestBody.fromBytes(
                            file.getBytes()));
        } catch (IOException e) {
            return ResponseDto.errorDto("Failed to upload file", e.getMessage());
        }

        String url = minIOEndpoint
                + "/"
                + bucket
                + "/"
                + key;

        return ResponseDto.<String>builder()
                .success(true)
                .data(url)
                .build();
    }

    @Override
    public boolean delete(String filePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ResponseDto<String> uploadShortcut(MultipartFile file) {
        String error = validateImage(file, FileUpload.MAX_SHORTCUT_IMAGE_SIZE_BYTES);
        if (error != null) {
            return ResponseDto.errorDto(error);
        }
        return upload(file, MinIO.SHORTCUTS_IMAGE_FOLDER);
    }

    @Override
    public ResponseDto<String> uploadWallpaper(MultipartFile file) {
        String error = validateImage(file, FileUpload.MAX_WALLPAPER_IMAGE_SIZE_BYTES);
        if (error != null) {
            return ResponseDto.errorDto(error);
        }
        return upload(file, MinIO.WALLPAPER_USER_PROFILE_IMAGE_FOLDER);
    }

    private String validateImage(MultipartFile file, long maxSizeBytes) {
        if (file == null || file.isEmpty()) {
            return "No file provided";
        }
        if (file.getSize() > maxSizeBytes) {
            return "File exceeds " + (maxSizeBytes / (1024 * 1024)) + "MB limit";
        }
        String extension = StringUtils.lowerCase(FilenameUtils.getExtension(file.getOriginalFilename()));
        if (StringUtils.isBlank(extension) || !FileUpload.ALLOWED_IMAGE_EXTENSIONS.contains(extension)) {
            return "Unsupported file extension";
        }
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith(FileUpload.IMAGE_CONTENT_TYPE_PREFIX)) {
            return "Invalid file type";
        }
        return null;
    }
}
