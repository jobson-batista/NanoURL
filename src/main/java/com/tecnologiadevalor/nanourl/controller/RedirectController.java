package com.tecnologiadevalor.nanourl.controller;

import com.tecnologiadevalor.nanourl.dto.UrlDto;
import com.tecnologiadevalor.nanourl.exception.ExpiredUrlException;
import com.tecnologiadevalor.nanourl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class RedirectController {

    @Autowired
    private UrlService urlService;

    @GetMapping("{shortCode}")
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
