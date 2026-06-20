package com.cursedbackend.services;

import java.util.List;
import java.util.UUID;

import com.cursedbackend.entities.CursedHomeConfiguration;

public interface CursedHomeConfigurationService {
    List<CursedHomeConfiguration> getConfigurations(String email);

    boolean createShortcut(CursedHomeConfiguration req);

    boolean editShortcut(UUID id, CursedHomeConfiguration req);

    boolean deleteShortcut(UUID id);
}
