package com.nuzhd.dto;

import java.time.LocalDateTime;

public record UrlStatisticsDto(String originalUrl,
                               String shortCode,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt,
                               Integer accessCount) {
}
