package com.cursedbackend.constants;

import java.util.Set;

public class FileUpload {
    private FileUpload() {
    }

    public static final long MAX_SHORTCUT_IMAGE_SIZE_BYTES = 5L * 1024 * 1024;   // 5MB
    public static final long MAX_WALLPAPER_IMAGE_SIZE_BYTES = 10L * 1024 * 1024; // 10MB

    public static final Set<String> ALLOWED_IMAGE_EXTENSIONS =
            Set.of("jpg", "jpeg", "png", "gif", "webp", "bmp");

    public static final String IMAGE_CONTENT_TYPE_PREFIX = "image/";
}
