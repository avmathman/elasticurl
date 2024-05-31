package com.avmathman.elasticurl.api.advices;

import com.avmathman.elasticurl.domain.BaseConverter.exception.BaseConverterException;
import com.avmathman.elasticurl.domain.SnowflakeIDGenerator.exception.SnowflakeIdGeneratorException;
import com.avmathman.elasticurl.domain.URLShortner.exception.URLNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * API global advice.
 */
@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Handles URLNotFoundException thrown by REST API methods.
     *
     * @param ex - exception instance.
     * @param request - request instance.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(URLNotFoundException.class)
    public ResponseEntity<Object> handleURLNotFoundException(URLNotFoundException ex, WebRequest request) {
        log.error("Object does not exist on {}: {}", request.getContextPath(), ex.getMessage());

        final ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.status(), request);
    }

    /**
     * Handles URLNotFoundException thrown by REST API methods.
     *
     * @param ex - exception instance.
     * @param request - request instance.
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    @ExceptionHandler(BaseConverterException.class)
    public ResponseEntity<Object> handleBaseConverterException(BaseConverterException ex, WebRequest request) {
        log.error("Object is null on {}: {}", request.getContextPath(), ex.getMessage());

        final ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.status(), request);
    }

    /**
     * Handles SnowflakeIdGeneratorException thrown by REST API methods.
     *
     * @param ex - exception instance.
     * @param request - request instance.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(SnowflakeIdGeneratorException.class)
    public ResponseEntity<Object> handleSnowflakeIdGeneratorException(SnowflakeIdGeneratorException ex, WebRequest request) {
        log.error("Object does not exist on {}: {}", request.getContextPath(), ex.getMessage());

        final ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.status(), request);
    }
}
