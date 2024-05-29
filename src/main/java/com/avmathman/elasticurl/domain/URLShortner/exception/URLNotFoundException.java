package com.avmathman.elasticurl.domain.URLShortner.exception;

/**
 * Occurs when requested URL can not be found.
 */
public class URLNotFoundException extends RuntimeException {

    /**
     * Initializes a new {@link URLNotFoundException} instance.
     *
     * @param message - error description message.
     */
    public URLNotFoundException(String message) {
        super(message);
    }
}
