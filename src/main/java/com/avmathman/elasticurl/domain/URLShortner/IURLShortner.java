package com.avmathman.elasticurl.domain.URLShortner;

import com.avmathman.elasticurl.api.controllers.URLShortner.dto.ShortnerURLDto;

public interface IURLShortner {

    /**
     * Returns shortened URL.
     *
     * @param dto - The {@link ShortnerURLDto} dto.
     * @return The shortened URL.
     */
    public URLShortnerModel shortenURL(ShortnerURLDto dto);

    /**
     * Returns actual url from persisted data.
     *
     * @param encodedId - The encoded ID.
     * @return The {@link URLShortnerModel} model with actual url from persisted data.
     */
    public URLShortnerModel getShortURL(String encodedId);
}
