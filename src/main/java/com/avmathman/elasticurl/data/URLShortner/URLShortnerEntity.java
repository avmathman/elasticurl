package com.avmathman.elasticurl.data.URLShortner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection="urlshortner")
public class URLShortnerEntity {

    /**
     * The user identifier.
     */
    @Id
    @Field(name = "id")
    private String id;

    /**
     * The encoded ID for shortened URL.
     */
    @Field(name = "shortURL")
    private String shortURL;

    /**
     * The actual URL.
     */
    @Field(name = "actualURL")
    private String actualURL;

    /**
     * The date when the encoded short URL was created.
     */
    @Field(name = "createdAt")
    @CreatedDate
    private Date createdAt;
}
