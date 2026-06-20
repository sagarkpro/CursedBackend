package com.cursedbackend.services;

import com.cursedbackend.entities.CursedHomeWallpapers;
import com.cursedbackend.respositories.CursedHomeWallpapersRepository;

public class CursedHomeWallpapersServiceImpl implements CursedHomeWallpapersService {
    private final CursedHomeWallpapersRepository cursedHomeWallpapersRepository;

    public CursedHomeWallpapersServiceImpl(CursedHomeWallpapersRepository cursedHomeWallpapersRepository) {
        this.cursedHomeWallpapersRepository = cursedHomeWallpapersRepository;
    }

    @Override
    public CursedHomeWallpapers getUserWallpapers(String email) {
        var wallpapers = cursedHomeWallpapersRepository.findByUserEmail(email);
        return wallpapers.orElse(null);
    }

    @Override
    public boolean updateWallpapers(String email, CursedHomeWallpapers req) {
        var existing = cursedHomeWallpapersRepository.findByUserEmail(email);
        if (existing.isEmpty()) {
            cursedHomeWallpapersRepository.save(req);
            return true;
        }
        existing.get().setWallpaperUrls(req.getWallpaperUrls());
        cursedHomeWallpapersRepository.save(existing.get());
        return true;
    }

}
