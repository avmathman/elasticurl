package com.avmathman.elasticurl.domain.URLShortner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * The model of the URL Shortner.
 */
@Getter
@Setter
@NoArgsConstructor
public class URLShortnerModel {

    /**
     * The user identifier.
     */
    private Long id;

    /**
     * The encoded ID for shortened URL.
     */
    private String shortURL;

    /**
     * The actual URL.
     */
    private String actualURL;

    /**
     * The date when the encoded short URL was created.
     */
    private Date createdAt;
}
