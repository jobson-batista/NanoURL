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
public class TotalAccessCountException extends RuntimeException {

    private String message = "Error counting accesses";
    private String description = "Cannot count amount of redirects";

    public TotalAccessCountException(String description) {
        this.description = description;
    }
}
