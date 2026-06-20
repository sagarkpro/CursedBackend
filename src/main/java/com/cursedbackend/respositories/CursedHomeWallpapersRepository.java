package com.cursedbackend.respositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursedbackend.entities.CursedHomeWallpapers;

public interface CursedHomeWallpapersRepository extends JpaRepository<CursedHomeWallpapers, UUID> {
    Optional<CursedHomeWallpapers> findByUserEmail(String email);
}
