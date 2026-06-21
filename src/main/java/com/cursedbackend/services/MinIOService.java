package com.cursedbackend.services;

import org.springframework.web.multipart.MultipartFile;

import com.cursedbackend.dtos.ResponseDto;

public interface MinIOService {
    ResponseDto<String> upload(MultipartFile file, String folder);

    ResponseDto<String> uploadShortcut(MultipartFile file);

    ResponseDto<String> uploadWallpaper(MultipartFile file);

    boolean delete(String filePath);
}
