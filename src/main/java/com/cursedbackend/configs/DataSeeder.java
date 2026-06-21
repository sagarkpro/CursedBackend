package com.cursedbackend.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cursedbackend.constants.Constants;
import com.cursedbackend.constants.DefaultPersonalization;
import com.cursedbackend.logging.CursedLogger;
import com.cursedbackend.respositories.PersonalizationConfigurationRepository;
import com.cursedbackend.respositories.PersonalizationWallpapersRepository;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PersonalizationConfigurationRepository configRepository;
    private final PersonalizationWallpapersRepository wallpaperRepository;

    public DataSeeder(PersonalizationConfigurationRepository configRepository,
            PersonalizationWallpapersRepository wallpaperRepository) {
        this.configRepository = configRepository;
        this.wallpaperRepository = wallpaperRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        CursedLogger.info("Data seeding started for user {}", Constants.DEFAULT_USER_EMAIL);
        seedConfig();
        seedWallpapers();
        CursedLogger.info("Data seeding finished");
    }

    private void seedConfig() {
        var existing = configRepository.existsByUserEmail(Constants.DEFAULT_USER_EMAIL);
        if (existing) {
            CursedLogger.info("Personalization config already present for {}, skipping",
                    Constants.DEFAULT_USER_EMAIL);
            return;
        }
        CursedLogger.info("Seeding {} personalization configs for {}",
                DefaultPersonalization.defaultConfigs.size(), Constants.DEFAULT_USER_EMAIL);
        configRepository.saveAll(DefaultPersonalization.defaultConfigs);
    }

    private void seedWallpapers() {
        var existing = wallpaperRepository.existsByUserEmail(Constants.DEFAULT_USER_EMAIL);
        if (existing) {
            CursedLogger.info("Personalization wallpapers already present for {}, skipping",
                    Constants.DEFAULT_USER_EMAIL);
            return;
        }
        CursedLogger.info("Seeding default wallpapers ({} urls) for {}",
                DefaultPersonalization.defaultWallpapers.getWallpaperUrls().size(),
                Constants.DEFAULT_USER_EMAIL);
        wallpaperRepository.save(DefaultPersonalization.defaultWallpapers);
    }

}
