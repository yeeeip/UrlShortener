package com.nuzhd.utils;

import com.nuzhd.dto.ShortenUrlResponse;
import com.nuzhd.dto.UrlStatisticsDto;
import com.nuzhd.model.ShortenedUrl;

import java.util.function.Function;

public class Mappers {

    public static Function<ShortenedUrl, ShortenUrlResponse> mapToUrlResponse =
            url -> new ShortenUrlResponse(
                    url.getOriginalUrl(),
                    url.getShortCode(),
                    url.getCreatedAt(),
                    url.getUpdatedAt()
            );

    public static Function<ShortenedUrl, UrlStatisticsDto> mapToUrlStats =
            url -> new UrlStatisticsDto(
                    url.getOriginalUrl(),
                    url.getShortCode(),
                    url.getCreatedAt(),
                    url.getUpdatedAt(),
                    url.getAccessCount()
            );
}
