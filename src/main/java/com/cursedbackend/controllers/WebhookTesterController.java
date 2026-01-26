package com.cursedbackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursedbackend.dtos.webhookTester.WebhookLogDto;
import com.cursedbackend.services.WebhookLogService;

@RestController
@RequestMapping("/webhook-tester")
public class WebhookTesterController {
    private final WebhookLogService webhookLogService;

    public WebhookTesterController(WebhookLogService webhookLogService){
        this.webhookLogService = webhookLogService;
    }

    @GetMapping
    public ResponseEntity<List<WebhookLogDto>> listLogs(){
        var logs = webhookLogService.listWebhooks();
        return ResponseEntity.ok(logs);
    }

    @PostMapping
    public ResponseEntity<WebhookLogDto> createLog(@RequestBody WebhookLogDto req){
        var res = webhookLogService.createWebhookLog(req);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<WebhookLogDto> deleteWebhookLog(@PathVariable String id){
        var logs = webhookLogService.deleteWebhookLog(id);
        return ResponseEntity.ok(logs);
    }
}
