package com.avmathman.elasticurl.api.advices;

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
    public ResponseEntity<Object> handleItemNotFoundException(URLNotFoundException ex, WebRequest request) {
        log.error("Object does not exist on {}: {}", request.getContextPath(), ex.getMessage());

        final ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.status(), request);
    }
}
