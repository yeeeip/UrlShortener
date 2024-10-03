package com.nuzhd.repo;

import com.nuzhd.model.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, UUID> {

    Optional<ShortenedUrl> findByOriginalUrl(String originalUrl);

    Optional<ShortenedUrl> findByShortCode(String shortCode);

    void deleteByShortCode(String shortCode);

}
