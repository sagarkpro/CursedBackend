package com.cursedbackend.services;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursedbackend.constants.Constants;
import com.cursedbackend.constants.DefaultPersonalization;
import com.cursedbackend.domain.ValidateReorderShortcutRes;
import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.dtos.personalization.PersonalizationConfigurationDto;
import com.cursedbackend.dtos.personalization.ReorderShortcutDto;
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
        var configs = configRepository.findByUserEmailOrderByRank(email);
        return ResponseDto.<List<PersonalizationConfigurationDto>>builder()
                .success(true)
                .data(configs.stream().map(this::toPersonalizationConfigurationDto).toList())
                .build();
    }

    @Override
    public ResponseDto<Void> createShortcut(String email, PersonalizationConfigurationDto req) {
        var exitingFirst = configRepository.findFirstByUserEmailOrderByRank(email).orElse(null);
        String newRank;
        if (exitingFirst != null) {
            newRank = new BigInteger(exitingFirst.getRank(), 36).divide(BigInteger.TWO).toString(36);
        } else {
            newRank = Constants.FIRST_SHORTCUT_RANK;
        }
        var config = toPersonalizationConfiguration(req, email);
        config.setRank(newRank);
        configRepository.save(config);
        return ResponseDto.successDto();
    }

    @Override
    @Transactional
    public ResponseDto<Void> initializeDefaults(String email) {
        configRepository.deleteByUserEmail(email);
        var seeds = DefaultPersonalization.defaultConfigs.stream()
                .map(c -> PersonalizationConfiguration.builder()
                        .userEmail(email)
                        .type(c.getType())
                        .name(c.getName())
                        .url(c.getUrl())
                        .image(c.getImage())
                        .build())
                .toList();
        configRepository.saveAll(seeds);
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

    @Override
    public ResponseDto<Void> reorderShortcut(String email, ReorderShortcutDto req) {
        var validationRes = validateReorderReq(email, req);
        if (!validationRes.isValid()) {
            return ResponseDto.errorDto(validationRes.getError());
        }

        String newRank = calculateLexoRank(validationRes.getCurrent(), validationRes.getPrev(),
                validationRes.getNext());
        validationRes.getCurrent().setRank(newRank);
        configRepository.save(validationRes.getCurrent());
        return ResponseDto.successDto();
    }

    private ValidateReorderShortcutRes validateReorderReq(String email, ReorderShortcutDto req) {
        if (req.getId() == null)
            return ValidateReorderShortcutRes.invalid("Invalid request, id is required");
        if (req.getNext() == null && req.getPrev() == null)
            return ValidateReorderShortcutRes.invalid("Invalid request, either prev or next is required");

        var current = configRepository.findByIdAndUserEmail(req.getId(), email).orElse(null);
        if (current == null)
            return ValidateReorderShortcutRes.invalid("Invalid id");

        PersonalizationConfiguration next = null;
        PersonalizationConfiguration prev = null;

        if (req.getNext() != null) {
            next = configRepository.findByIdAndUserEmail(req.getNext(), email).orElse(null);
            if (next == null)
                return ValidateReorderShortcutRes.invalid("Invalid nextId");
        }

        if (req.getPrev() != null) {
            prev = configRepository.findByIdAndUserEmail(req.getPrev(), email).orElse(null);
            if (prev == null)
                return ValidateReorderShortcutRes.invalid("Invalid prevId");
        }

        return ValidateReorderShortcutRes.builder()
                .valid(true)
                .current(current)
                .prev(prev)
                .next(next)
                .build();
    }

    private String calculateLexoRank(PersonalizationConfiguration current, PersonalizationConfiguration prev,
            PersonalizationConfiguration next) {

        BigInteger prevRank;
        BigInteger nextRank;

        if (prev != null && next != null) {
            prevRank = new BigInteger(prev.getRank(), 36);
            nextRank = new BigInteger(next.getRank(), 36);
        } else if (prev != null) {
            prevRank = new BigInteger(prev.getRank(), 36);
            nextRank = new BigInteger(current.getRank(), 36);
        } else {
            nextRank = new BigInteger(next.getRank(), 36);
            prevRank = new BigInteger(current.getRank(), 36);
        }
        var mid = prevRank.add(nextRank).divide(BigInteger.TWO);
        return mid.toString(36);
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
