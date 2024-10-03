package com.nuzhd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShortenUrlDto(@JsonProperty("url") String url) {
}
