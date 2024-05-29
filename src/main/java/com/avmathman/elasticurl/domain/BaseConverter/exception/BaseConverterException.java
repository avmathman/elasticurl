package com.avmathman.elasticurl.domain.BaseConverter.exception;

/**
 * Occurs when encoding or decoding conversion occurs.
 */
public class BaseConverterException extends RuntimeException {

    /**
     * Initializes a new {@link BaseConverterException} instance.
     *
     * @param message - error description message.
     */
    public BaseConverterException(String message) {
        super(message);
    }
}
