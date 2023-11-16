package com.blog.search.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    private static final String INTERNAL_ERROR = "서버 오류가 발생하였습니다. {}";

    @org.springframework.web.bind.annotation.ExceptionHandler(KeywordNotFoundException.class)
    public ResponseEntity<?> notFoundException(final Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class,
                                                               MissingServletRequestParameterException.class})
    public ResponseEntity<String> handleConstraintViolationException(final Exception e) {
        return new ResponseEntity<>("Validation failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handleAll(final Exception e) {
        log.error("Internal Server Error, {}", e.getMessage());
        return ResponseEntity.internalServerError().body(MessageFormat.format(INTERNAL_ERROR, e.getMessage()));
    }

}
