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

    @ExceptionHandler(ExpiredUrlException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<MessageError> expiredUrlException(ExpiredUrlException expiredUrlException, WebRequest request) {
        expiredUrlException.printStackTrace();
        MessageError message = new MessageError(
                HttpStatus.BAD_REQUEST.value(),
                this.baseUrl,
                expiredUrlException.getMessage(),
                expiredUrlException.getDescription(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShortCodeLengthException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<MessageError> shortCodeLengthException(ShortCodeLengthException shortCodeLengthException, WebRequest request) {
        shortCodeLengthException.printStackTrace();
        MessageError message = new MessageError(
                HttpStatus.BAD_REQUEST.value(),
                this.baseUrl,
                shortCodeLengthException.getMessage(),
                shortCodeLengthException.getDescription(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TotalAccessCountException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<MessageError> totalAccessCountException(TotalAccessCountException totalAccessCountException, WebRequest request) {
        totalAccessCountException.printStackTrace();
        MessageError message = new MessageError(
                HttpStatus.BAD_REQUEST.value(),
                this.baseUrl,
                totalAccessCountException.getMessage(),
                totalAccessCountException.getDescription(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
