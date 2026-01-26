package com.cursedbackend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cursedbackend.dtos.webhookTester.WebhookLogDto;
import com.cursedbackend.entities.WebhookLogs;
import com.cursedbackend.logging.CursedLogger;
import com.cursedbackend.respositories.WebhookLogsRepository;

@Service
public class WebhookLogServiceImpl implements WebhookLogService {
    private final WebhookLogsRepository webhookLogsRepository;

    public WebhookLogServiceImpl(WebhookLogsRepository webhookLogsRepository) {
        this.webhookLogsRepository = webhookLogsRepository;
    }

    @Override
    public List<WebhookLogDto> listWebhooks() {
        var logs = webhookLogsRepository.findAll();
        return logs.stream().map(log -> WebhookLogDto.builder()
            .id(log.getId())
            .createdAt(log.getCreatedAt())
            .logItem(log.getLogItem())
            .build())
        .collect(Collectors.toList());
    }

    @Override
    public WebhookLogDto deleteWebhookLog(String id) {
        if(StringUtils.isBlank(id)){
           throw new IllegalArgumentException("Id is required to delete webhook"); 
        }
        var uuid = UUID.fromString(id);

        var existingWebhook = webhookLogsRepository.findById(uuid);
        if(existingWebhook.isEmpty()){
            throw new IllegalArgumentException("Webhook you are trying to delete doesn't exist.");
        }
        webhookLogsRepository.deleteById(uuid);
        return WebhookLogDto.builder()
            .logItem(existingWebhook.get().getLogItem())
            .id(existingWebhook.get().getId())
            .createdAt(existingWebhook.get().getCreatedAt())
            .build();
    }

    @Override
    public WebhookLogDto createWebhookLog(WebhookLogDto webhookLogDto) {
        var res = webhookLogsRepository.save(WebhookLogs.builder()
                .logItem(webhookLogDto.getLogItem())
                .createdAt(LocalDateTime.now())
                .build());

        return WebhookLogDto.builder()
            .logItem(res.getLogItem())
            .createdAt(res.getCreatedAt())
            .id(res.getId())
            .build();
    }

}
