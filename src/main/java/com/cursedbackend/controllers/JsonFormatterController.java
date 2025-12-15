package com.cursedbackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.NotImplemented;
import org.springframework.web.multipart.MultipartFile;

import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.logging.CursedLogger;
import com.cursedbackend.utils.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.nio.file.Path;
import java.time.Instant;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/json-formatter")
public class JsonFormatterController {
    private final ObjectMapper objectMapper;

    public JsonFormatterController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/format-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Resource> formatFile(@RequestBody MultipartFile jsonFile) {
        CursedLogger.info("Received req to format");
        if (jsonFile.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!jsonFile.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new IllegalArgumentException("Only JSON files are allowed");
        }

        String attachment = "attachment; filename=\"" + jsonFile.getName() + "_" + Instant.now().toEpochMilli()
                + ".json\"";

        try (InputStream is = jsonFile.getInputStream()) {
            JsonNode jsonNode = objectMapper.readTree(is);
            byte[] opStream = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsBytes(jsonNode);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, attachment)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ByteArrayResource(opStream));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
