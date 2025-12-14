package com.cursedbackend.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    public static Path writeStringToFile(String content) throws IOException {
        Path filePath = Path.of("output.txt");
        Files.writeString(filePath, content, StandardCharsets.UTF_8);
        return filePath;
    }
}
