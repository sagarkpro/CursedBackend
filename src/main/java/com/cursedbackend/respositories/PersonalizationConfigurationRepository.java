package com.cursedbackend.respositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursedbackend.entities.PersonalizationConfiguration;

public interface PersonalizationConfigurationRepository extends JpaRepository<PersonalizationConfiguration, UUID> {
    List<PersonalizationConfiguration> findByUserEmail(String email);

    Optional<PersonalizationConfiguration> findByIdAndUserEmail(UUID id, String email);

    boolean existsByIdAndUserEmail(UUID id, String email);

    boolean existsByUserEmail(String email);

    void deleteByUserEmail(String email);
}
