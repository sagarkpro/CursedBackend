package com.cursedbackend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursedbackend.constants.Constants;
import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.dtos.personalization.PersonalizationConfigurationDto;
import com.cursedbackend.services.PersonalizationConfigurationService;
import com.cursedbackend.services.PersonalizationWallpapersService;
import com.cursedbackend.utils.CommonUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/personalization")
@Validated
public class PersonalizationController {
    private final PersonalizationConfigurationService configService;
    private final PersonalizationWallpapersService wallpaperService;

    public PersonalizationController(PersonalizationConfigurationService configService,
            PersonalizationWallpapersService wallpaperService) {
        this.configService = configService;
        this.wallpaperService = wallpaperService;
    }

    @GetMapping("config")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<List<PersonalizationConfigurationDto>>> getUserPersonalizations() {
        return CommonUtils.handleResponse(configService.getConfigurations(CommonUtils.getCurrentUserEmail()));
    }

    @GetMapping("config/default")
    public ResponseEntity<ResponseDto<List<PersonalizationConfigurationDto>>> getDefaultPersonalizations() {
        return CommonUtils.handleResponse(configService.getConfigurations(Constants.DEFAULT_USER_EMAIL));
    }

    @PostMapping("config")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<Void>> createPersonalization(
            @RequestBody @Valid PersonalizationConfigurationDto req) {
        return CommonUtils.handleResponse(configService.createShortcut(CommonUtils.getCurrentUserEmail(), req));
    }

    @PutMapping("{id}/config")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<Void>> createPersonalization(
            @PathVariable UUID id, @RequestBody @Valid PersonalizationConfigurationDto req) {
        return CommonUtils.handleResponse(configService.editShortcut(id, CommonUtils.getCurrentUserEmail(), req));
    }

    @DeleteMapping("{id}/config")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<Void>> createPersonalization(@PathVariable UUID id) {
        return CommonUtils.handleResponse(configService.deleteShortcut(id, CommonUtils.getCurrentUserEmail()));
    }

    @GetMapping("wallpapers")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<List<String>>> getUserWallpapers() {
        return CommonUtils.handleResponse(wallpaperService.getUserWallpapers(CommonUtils.getCurrentUserEmail()));
    }

    @GetMapping("wallpapers/default")
    public ResponseEntity<ResponseDto<List<String>>> getDefaultWallpapers() {
        return CommonUtils.handleResponse(wallpaperService.getUserWallpapers(Constants.DEFAULT_USER_EMAIL));
    }

    @PostMapping("wallpapers")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<Void>> updateWallpapers(@RequestBody List<String> req) {
        return CommonUtils.handleResponse(wallpaperService.updateWallpapers(CommonUtils.getCurrentUserEmail(), req));
    }
}
