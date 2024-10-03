package com.nuzhd.service.impl;

import com.nuzhd.dto.ShortenUrlDto;
import com.nuzhd.dto.ShortenUrlResponse;
import com.nuzhd.dto.UrlStatisticsDto;
import com.nuzhd.exception.ShortenedUrlNotFoundException;
import com.nuzhd.model.ShortenedUrl;
import com.nuzhd.repo.ShortenedUrlRepository;
import com.nuzhd.service.URLShortenerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.nuzhd.utils.Mappers.mapToUrlResponse;
import static com.nuzhd.utils.Mappers.mapToUrlStats;
import static com.nuzhd.utils.UrlUtils.generateShortenedUrl;

@Service
public class URLShortenerServiceImpl implements URLShortenerService {

    private final ShortenedUrlRepository urlRepository;

    public URLShortenerServiceImpl(ShortenedUrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public ShortenUrlResponse shortenUrl(ShortenUrlDto shortenUrlDto) {

        return urlRepository.findByOriginalUrl(shortenUrlDto.url())
                .map(mapToUrlResponse)
                .orElseGet(() -> {
                    ShortenedUrl url = new ShortenedUrl(
                            shortenUrlDto.url(),
                            generateShortenedUrl(shortenUrlDto.url())
                    );

                    return mapToUrlResponse.apply(urlRepository.save(url));
                });
    }

    @Override
    @Transactional
    public ShortenUrlResponse updateUrl(String shortCode, ShortenUrlDto shortenUrlDto) {
        ShortenedUrl foundUrl = findByShortCodeOrThrow(shortCode);

        foundUrl.setOriginalUrl(shortenUrlDto.url());

        ShortenedUrl updatedUrl = urlRepository.save(foundUrl);

        return mapToUrlResponse.apply(updatedUrl);
    }

    @Override
    public void deleteUrl(String shortCode) {
        urlRepository.delete(findByShortCodeOrThrow(shortCode));
    }

    @Override
    public UrlStatisticsDto getUrlStats(String shortCode) {
        ShortenedUrl url = findByShortCodeOrThrow(shortCode);

        return mapToUrlStats.apply(url);
    }

    @Override
    @Transactional
    public ShortenUrlResponse getOriginalUrl(String shortCode) {
        ShortenedUrl url = findByShortCodeOrThrow(shortCode);

        incrementAccessCount(url);

        return mapToUrlResponse.apply(url);
    }

    @Override
    public void incrementAccessCount(ShortenedUrl shortenedUrl) {
        shortenedUrl.setAccessCount(shortenedUrl.getAccessCount() + 1);

        urlRepository.save(shortenedUrl);
    }

    private ShortenedUrl findByShortCodeOrThrow(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortenedUrlNotFoundException("Short URL %s not found".formatted(shortCode)));
    }
}
