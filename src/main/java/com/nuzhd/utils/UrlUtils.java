package com.nuzhd.utils;

import java.util.UUID;

public class UrlUtils {

    public static String generateShortenedUrl(String originalUrl) {
        return UUID.nameUUIDFromBytes(originalUrl.getBytes())
                .toString()
                .substring(25);
    }

}
