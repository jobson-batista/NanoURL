package com.tecnologiadevalor.nanourl.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    private String message = "Not Found";
    private String description = "Check the ID entered.";

    public NotFoundException(String description) {
        this.description = description;
    }

}
