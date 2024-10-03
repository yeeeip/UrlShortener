package com.nuzhd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShortenedUrlNotFoundException extends RuntimeException{

    public ShortenedUrlNotFoundException(String message) {
        super(message);
    }
}
