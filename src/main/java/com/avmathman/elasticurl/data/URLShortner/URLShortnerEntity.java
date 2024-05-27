package com.avmathman.elasticurl.data.URLShortner;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

public class URLShortnerEntity {

    /**
     * The user identifier.
     */
    @Id
    @Column(name = "id")
    @NotNull
    private Long id;

    /**
     * The encoded ID for shortened URL.
     */
    @Column(name = "shortUrl")
    @NotNull
    private String shortURL;

    /**
     * The actual URL.
     */
    @Column(name = "actualUrl")
    @NotNull
    private String actualURL;

    /**
     * The date when the encoded short URL was created.
     */
    @Column(name = "createdAt")
    @CreationTimestamp
    @NotNull
    private Date createdAt;
}
