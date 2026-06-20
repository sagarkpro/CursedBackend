package com.cursedbackend.services;

import com.cursedbackend.entities.CursedHomeWallpapers;

public interface CursedHomeWallpapersService {
    CursedHomeWallpapers getUserWallpapers(String email);

    boolean updateWallpapers(String email, CursedHomeWallpapers req);
}
