package com.nuzhd.service;

import com.nuzhd.dto.ShortenUrlDto;
import com.nuzhd.dto.ShortenUrlResponse;
import com.nuzhd.dto.UrlStatisticsDto;
import com.nuzhd.model.ShortenedUrl;

public interface URLShortenerService {

    ShortenUrlResponse shortenUrl(ShortenUrlDto shortenUrlDto);

    ShortenUrlResponse updateUrl(String shortCode, ShortenUrlDto shortenUrlDto);

    void deleteUrl(String shortCode);

    UrlStatisticsDto getUrlStats(String shortCode);

    ShortenUrlResponse getOriginalUrl(String shortCode);

    void incrementAccessCount(ShortenedUrl shortenedUrl);
}
