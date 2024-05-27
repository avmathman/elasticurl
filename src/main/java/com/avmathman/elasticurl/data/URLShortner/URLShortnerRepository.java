package com.avmathman.elasticurl.data.URLShortner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Data repository for URLs.
 */
@Repository
public interface URLShortnerRepository extends JpaRepository<URLShortnerEntity, Long> {
    Optional<URLShortnerEntity> findByShortUrl(String shortUrl);
}
