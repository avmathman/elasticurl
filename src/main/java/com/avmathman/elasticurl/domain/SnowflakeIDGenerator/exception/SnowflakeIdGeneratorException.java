package com.avmathman.elasticurl.domain.SnowflakeIDGenerator.exception;

/**
 * Occurs when Snowflake ID generator executed.
 */
public class SnowflakeIdGeneratorException extends RuntimeException {

    /**
     * Initializes a new {@link SnowflakeIdGeneratorException} instance.
     *
     * @param message - error description message.
     */
    public SnowflakeIdGeneratorException(String message) {
        super(message);
    }
}
