package com.cursedbackend.services;

import java.util.List;

import com.cursedbackend.dtos.ResponseDto;

public interface PersonalizationWallpapersService {
    ResponseDto<List<String>> getUserWallpapers(String email);

    ResponseDto<Void> updateWallpapers(String email, List<String> req);
}
