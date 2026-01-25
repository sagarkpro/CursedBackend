package com.cursedbackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cursedbackend.dtos.webhookTester.WebhookLogDto;

public interface WebhookLogService {
    public List<WebhookLogDto> listWebhooks();
    public WebhookLogDto deleteWebhookLog(String id);
}
