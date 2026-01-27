package com.cursedbackend.services;

import java.util.List;

import com.cursedbackend.dtos.PaginatedResponseDto;
import com.cursedbackend.dtos.webhookTester.WebhookLogDto;

public interface WebhookLogService {
    public PaginatedResponseDto<List<WebhookLogDto>> listWebhooks();
    public PaginatedResponseDto<List<WebhookLogDto>> listWebhooks(int pageNum, int pageSize);
    public WebhookLogDto deleteWebhookLog(String id);
    public WebhookLogDto createWebhookLog(WebhookLogDto webhookLogDto);
}
