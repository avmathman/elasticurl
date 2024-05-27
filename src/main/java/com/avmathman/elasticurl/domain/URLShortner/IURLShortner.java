package com.avmathman.elasticurl.domain.URLShortner;

import com.avmathman.elasticurl.domain.URLShortner.dto.ShortnerURLDto;

public interface IURLShortner {

    /**
     * Returns shortened URL.
     *
     * @param dto - The {@link ShortnerURLDto} dto.
     * @return The shortened URL.
     */
    public String shortenURL(ShortnerURLDto dto);

    /**
     * Returns actual url from persisted data.
     *
     * @param encodedId - The encoded ID.
     * @return The actual url from persisted data.
     */
    public String getShortURL(String encodedId);
}
