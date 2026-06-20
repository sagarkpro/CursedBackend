package com.cursedbackend.services;

import java.util.List;
import java.util.UUID;

import com.cursedbackend.entities.CursedHomeConfiguration;
import com.cursedbackend.respositories.CursedHomeConfigurationRepository;

public class CursedHomeConfigurationServiceImpl implements CursedHomeConfigurationService {
    private final CursedHomeConfigurationRepository configRepository;

    public CursedHomeConfigurationServiceImpl(CursedHomeConfigurationRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public List<CursedHomeConfiguration> getConfigurations(String email) {
        return configRepository.findByUserEmail(email);
    }

    @Override
    public boolean createShortcut(CursedHomeConfiguration req) {
        configRepository.save(req);
        return true;
    }

    @Override
    public boolean editShortcut(UUID id, CursedHomeConfiguration req) {
        if (!configRepository.existsById(id)) {
            return false;
        }
        configRepository.save(req);
        return true;
    }

    @Override
    public boolean deleteShortcut(UUID id) {
        if (!configRepository.existsById(id)) {
            return false;
        }
        configRepository.deleteById(id);
        return true;
    }

}
