package com.cursedbackend.respositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursedbackend.entities.WebhookLogs;

public interface WebhookLogsRepository extends JpaRepository<WebhookLogs, UUID> {
    
}
