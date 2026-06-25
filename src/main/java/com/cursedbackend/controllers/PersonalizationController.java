package com.cursedbackend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cursedbackend.constants.Constants;
import com.cursedbackend.constants.DefaultPersonalization;
import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.dtos.personalization.PersonalizationConfigurationDto;
import com.cursedbackend.dtos.personalization.ReorderShortcutDto;
import com.cursedbackend.services.MinIOService;
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
    private final MinIOService minIOService;

    public PersonalizationController(PersonalizationConfigurationService configService,
            PersonalizationWallpapersService wallpaperService,
            MinIOService minIOService) {
        this.configService = configService;
        this.wallpaperService = wallpaperService;
        this.minIOService = minIOService;
    }

    @PostMapping("initialize-defaults")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<Void>> initializeDefaults() {
        var email = CommonUtils.getCurrentUserEmail();
        wallpaperService.updateWallpapers(email, DefaultPersonalization.defaultWallpapers.getWallpaperUrls());
        return CommonUtils.handleResponse(configService.initializeDefaults(email));
    }

    @GetMapping("shortcut")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<List<PersonalizationConfigurationDto>>> getUserPersonalizations() {
        return CommonUtils.handleResponse(configService.getConfigurations(CommonUtils.getCurrentUserEmail()));
    }

    @GetMapping("shortcut/default")
    public ResponseEntity<ResponseDto<List<PersonalizationConfigurationDto>>> getDefaultPersonalizations() {
        return CommonUtils.handleResponse(configService.getConfigurations(Constants.DEFAULT_USER_EMAIL));
    }

    @PostMapping("shortcut")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<Void>> createPersonalization(
            @RequestBody @Valid PersonalizationConfigurationDto req) {
        return CommonUtils.handleResponse(configService.createShortcut(CommonUtils.getCurrentUserEmail(), req));
    }

    @PostMapping("shortcut/reorder")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<Void>> reorder(@RequestBody @Valid ReorderShortcutDto req) {
        return CommonUtils.handleResponse(configService.reorderShortcut(CommonUtils.getCurrentUserEmail(), req));
    }

    @PutMapping("{id}/shortcut")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<Void>> createPersonalization(
            @PathVariable UUID id, @RequestBody @Valid PersonalizationConfigurationDto req) {
        return CommonUtils.handleResponse(configService.editShortcut(id, CommonUtils.getCurrentUserEmail(), req));
    }

    @DeleteMapping("{id}/shortcut")
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

    @PostMapping(value = "shortcuts/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<String>> uploadShortcutImage(@RequestPart("file") MultipartFile file) {
        return CommonUtils.handleResponse(minIOService.uploadShortcut(file));
    }

    @PostMapping(value = "wallpapers/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseDto<String>> uploadWallpaperImage(@RequestPart("file") MultipartFile file) {
        return CommonUtils.handleResponse(minIOService.uploadWallpaper(file));
    }
}
