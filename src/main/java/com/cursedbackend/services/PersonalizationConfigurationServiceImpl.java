package com.cursedbackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.dtos.personalization.PersonalizationConfigurationDto;
import com.cursedbackend.entities.PersonalizationConfiguration;
import com.cursedbackend.respositories.PersonalizationConfigurationRepository;

@Service
public class PersonalizationConfigurationServiceImpl implements PersonalizationConfigurationService {
    private final PersonalizationConfigurationRepository configRepository;

    public PersonalizationConfigurationServiceImpl(PersonalizationConfigurationRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public ResponseDto<Void> deleteShortcut(UUID id, String email) {
        if (!configRepository.existsByIdAndUserEmail(id, email)) {
            return ResponseDto.errorDto("Invalid id, resource not found");
        }
        configRepository.deleteById(id);
        return ResponseDto.successDto();
    }

    @Override
    public ResponseDto<List<PersonalizationConfigurationDto>> getConfigurations(String email) {
        var configs = configRepository.findByUserEmail(email);
        return ResponseDto.<List<PersonalizationConfigurationDto>>builder()
                .success(true)
                .data(configs.stream().map(this::toPersonalizationConfigurationDto).toList())
                .build();
    }

    @Override
    public ResponseDto<Void> createShortcut(String email, PersonalizationConfigurationDto req) {
        var config = toPersonalizationConfiguration(req, email);
        configRepository.save(config);
        return ResponseDto.successDto();
    }

    @Override
    public ResponseDto<Void> editShortcut(UUID id, String email, PersonalizationConfigurationDto req) {
        var existing = configRepository.findByIdAndUserEmail(id, email).orElse(null);
        if (existing != null) {
            var edited = toPersonalizationConfiguration(req, existing.getUserEmail(), existing.getId());
            configRepository.save(edited);
            return ResponseDto.successDto();
        }
        return ResponseDto.errorDto("Invalid id, missing resource");
    }

    private PersonalizationConfigurationDto toPersonalizationConfigurationDto(PersonalizationConfiguration config) {
        if (config != null) {
            return PersonalizationConfigurationDto.builder()
                    .id(config.getId())
                    .image(config.getImage())
                    .name(config.getName())
                    .type(config.getType())
                    .url(config.getUrl())
                    .build();
        }
        return null;
    }

    private PersonalizationConfiguration toPersonalizationConfiguration(PersonalizationConfigurationDto config,
            String userEmail) {
        if (config != null) {
            return PersonalizationConfiguration.builder()
                    .id(config.getId())
                    .userEmail(userEmail)
                    .image(config.getImage())
                    .name(config.getName())
                    .type(config.getType())
                    .url(config.getUrl())
                    .build();
        }
        return null;
    }

    private PersonalizationConfiguration toPersonalizationConfiguration(PersonalizationConfigurationDto config,
            String userEmail,
            UUID id) {
        var edited = toPersonalizationConfiguration(config, userEmail);
        edited.setId(id);
        return edited;
    }

}
