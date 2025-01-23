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
@RequestMapping("/io")
@Tag(name = "Redirect Controller", description = "Module for redirect to original URL.")
public class RedirectController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/{shortCode}")
    @Operation(summary = "Redirect", description = "Redirects to original URL from shortcode")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortCode) throws ExpiredUrlException {
        UrlDto originalUrl = urlService.getOriginalUrl(shortCode);

        if(originalUrl != null) {
            if(originalUrl.getExpiresAt().isBefore(LocalDateTime.now())) {
                throw new ExpiredUrlException();
            }
        }

        assert originalUrl != null;

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl.getOriginalUrl())).build();
    }
}
