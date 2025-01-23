package com.tecnologiadevalor.nanourl.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UrlExceptionHandler {


    @Value("${app.base-url}")
    private String baseUrl;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MessageError> globalException(Exception exception, WebRequest request) {
        exception.printStackTrace();
        MessageError message = new MessageError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false),
                "Internal server error",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<MessageError> notFoundException(NotFoundException notFoundException, WebRequest request) {
        notFoundException.printStackTrace();
        MessageError message = new MessageError(
                HttpStatus.NOT_FOUND.value(),
                this.baseUrl,
                notFoundException.getMessage(),
                notFoundException.getDescription(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
