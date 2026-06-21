package com.cursedbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.entities.PersonalizationWallpapers;
import com.cursedbackend.respositories.PersonalizationWallpapersRepository;

@Service
public class PersonalizationWallpapersServiceImpl implements PersonalizationWallpapersService {
    private final PersonalizationWallpapersRepository wallpaperRepository;

    public PersonalizationWallpapersServiceImpl(
            PersonalizationWallpapersRepository personalizationWallpapersRepository) {
        this.wallpaperRepository = personalizationWallpapersRepository;
    }

    @Override
    public ResponseDto<List<String>> getUserWallpapers(String email) {
        var wallpapers = wallpaperRepository.findByUserEmail(email).orElse(null);
        List<String> res;
        if (wallpapers == null)
            res = new ArrayList<>();
        else
            res = wallpapers.getWallpaperUrls();

        return ResponseDto.<List<String>>builder()
                .success(true)
                .data(res)
                .build();
    }

    @Override
    public ResponseDto<Void> updateWallpapers(String email, List<String> req) {
        var existing = wallpaperRepository.findByUserEmail(email);
        if (existing.isEmpty()) {
            wallpaperRepository.save(PersonalizationWallpapers
                    .builder()
                    .userEmail(email)
                    .wallpaperUrls(req)
                    .build());
            return ResponseDto.successDto();
        }
        existing.get().setWallpaperUrls(req);
        wallpaperRepository.save(existing.get());
        return ResponseDto.successDto();
    }

}
