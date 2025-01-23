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
public class ExpiredUrlException extends RuntimeException {

    private String message = "Expired";
    private String description = "This shortened URL has expired";

    public ExpiredUrlException(String description) {
        this.description = description;
    }
}
