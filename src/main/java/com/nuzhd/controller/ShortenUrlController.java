package com.nuzhd.controller;

import com.nuzhd.dto.ShortenUrlDto;
import com.nuzhd.dto.ShortenUrlResponse;
import com.nuzhd.dto.UrlStatisticsDto;
import com.nuzhd.service.URLShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shortener/shorten")
public class ShortenUrlController {

    private final URLShortenerService urlShortenerService;

    public ShortenUrlController(URLShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlDto request) {
        ShortenUrlResponse response = urlShortenerService.shortenUrl(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<ShortenUrlResponse> getShortenUrl(@PathVariable String shortCode) {
        ShortenUrlResponse response = urlShortenerService.getOriginalUrl(shortCode);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<ShortenUrlResponse> updateShortenUrl(@PathVariable String shortCode, @RequestBody ShortenUrlDto request) {
        ShortenUrlResponse response = urlShortenerService.updateUrl(shortCode, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteShortenUrl(@PathVariable String shortCode) {
        urlShortenerService.deleteUrl(shortCode);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<UrlStatisticsDto> getShortenUrlStats(@PathVariable String shortCode) {
        UrlStatisticsDto statisticsDto = urlShortenerService.getUrlStats(shortCode);

        return ResponseEntity.ok(statisticsDto);
    }
}
