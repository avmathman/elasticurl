package com.avmathman.elasticurl.data.URLShortner;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Data repository for URLs.
 */
@Repository
public interface URLShortnerRepository extends MongoRepository<URLShortnerEntity, Long> {
    Optional<URLShortnerEntity> findByShortURL(String shortUrl);
}
