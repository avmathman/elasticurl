package com.avmathman.elasticurl.api.advices;

import org.springframework.http.HttpStatus;

/**
 * Common API error response object.
 *
 * @param status - the HTTP status code related to the error.
 * @param message - the error message occurred.
 */
public record ApiErrorResponse(HttpStatus status, String message) { }
