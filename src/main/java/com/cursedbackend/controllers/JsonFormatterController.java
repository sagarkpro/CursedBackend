package com.cursedbackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.cursedbackend.logging.CursedLogger;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.InputStream;
import java.time.Instant;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/json-formatter")
public class JsonFormatterController {
    private final ObjectMapper objectMapper;

    public JsonFormatterController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/format-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StreamingResponseBody> formatFile(
            @RequestPart("jsonFile") MultipartFile jsonFile) {
        CursedLogger.info("Received req to format");

        if (jsonFile.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!jsonFile.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new IllegalArgumentException("Only JSON files are allowed");
        }

        String attachment = "attachment; filename=\"" +
                jsonFile.getOriginalFilename().replace(".json", "") + "_"
                + Instant.now().toEpochMilli() + ".json\"";

        StreamingResponseBody stream = outputStream -> {
            try (
                    InputStream is = jsonFile.getInputStream();
                    JsonGenerator generator = objectMapper
                            .getFactory()
                            .createGenerator(outputStream)) {
                JsonNode jsonNode = objectMapper.readTree(is);

                ObjectWriter writer = objectMapper
                        .writerWithDefaultPrettyPrinter();

                writer.writeValue(generator, jsonNode);
                generator.flush(); // important for browsers
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid JSON");
            }
        };

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, attachment)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(stream);
    }

}
