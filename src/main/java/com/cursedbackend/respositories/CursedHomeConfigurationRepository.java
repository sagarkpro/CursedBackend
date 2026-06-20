package com.cursedbackend.respositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursedbackend.entities.CursedHomeConfiguration;

public interface CursedHomeConfigurationRepository extends JpaRepository<CursedHomeConfiguration, UUID> {
    List<CursedHomeConfiguration> findByUserEmail(String email);
}
