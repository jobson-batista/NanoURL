package com.tecnologiadevalor.nanourl.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShortCodeLengthException extends RuntimeException {

    private String message = "Invalid ShortCode size";
    private String description = "ShortCode must be 6 characters long";

    public ShortCodeLengthException(String description) {
        this.description = description;
    }
}
