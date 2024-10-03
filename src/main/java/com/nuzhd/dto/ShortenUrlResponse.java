package com.nuzhd.dto;

import java.time.LocalDateTime;

public record ShortenUrlResponse(String originalUrl,
                                 String shortCode,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt) {
}
