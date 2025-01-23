package com.tecnologiadevalor.nanourl.controller;

import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestBody Url url) {
        Url newUrl = urlService.createShortUrl(url.getOriginalUrl());
        return ResponseEntity.ok(newUrl);
    }

    @GetMapping("{shortUrl}")
    public ResponseEntity<Url> getOriginalUrl(@PathVariable String shortUrl) {
        Url originalUrl = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.ok(originalUrl);
    }
}
