package com.cursedbackend.services;

import java.util.List;
import java.util.UUID;

import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.dtos.personalization.PersonalizationConfigurationDto;

public interface PersonalizationConfigurationService {
    ResponseDto<List<PersonalizationConfigurationDto>> getConfigurations(String email);

    ResponseDto<Void> createShortcut(String email, PersonalizationConfigurationDto req);

    ResponseDto<Void> editShortcut(UUID id, String email, PersonalizationConfigurationDto req);

    ResponseDto<Void> deleteShortcut(UUID id, String email);
}
