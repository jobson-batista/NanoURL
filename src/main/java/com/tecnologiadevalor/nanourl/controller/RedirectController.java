package com.tecnologiadevalor.nanourl.controller;

import com.tecnologiadevalor.nanourl.dto.UrlDto;
import com.tecnologiadevalor.nanourl.exception.ExpiredUrlException;
import com.tecnologiadevalor.nanourl.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping
@Tag(
        name = "Redirect Controller",
        description = "Controller responsible for handling redirection requests. " +
                "Provides functionality to redirect users from a shortened URL (shortCode) " +
                "to its original URL, ensuring validity and expiration checks."
)
public class RedirectController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/{shortCode}")
    @Operation(
            summary = "Redirect to Original URL",
            description = "Handles requests to redirect a shortened URL (shortCode) to its corresponding original URL. " +
                    "If the shortened URL is expired, an exception is thrown."
    )
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortCode) throws ExpiredUrlException {
        UrlDto originalUrl = urlService.getOriginalUrl(shortCode);

        if(originalUrl.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredUrlException();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl.getOriginalUrl())).build();
    }
}
