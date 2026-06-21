package com.cursedbackend.respositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursedbackend.entities.PersonalizationWallpapers;

public interface PersonalizationWallpapersRepository extends JpaRepository<PersonalizationWallpapers, UUID> {
    Optional<PersonalizationWallpapers> findByUserEmail(String email);

    boolean existsByUserEmail(String email);
}
