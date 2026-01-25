package com.cursedbackend.dtos.webhookTester;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebhookLogDto {
    UUID id;
    LocalDateTime cratedAt;
    JsonNode LogItem;
}
