package com.tecnologiadevalor.nanourl.controller;

import com.tecnologiadevalor.nanourl.dto.UrlDto;
import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/url")
@Tag(name = "URL Controller", description = "Module for shortening URLs and performing CRUD operations")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    @Operation(summary = "Shorten", description = "Generate a short code for original URL")
    public ResponseEntity<UrlDto> shortenUrl(@RequestBody Url url) {
        UrlDto newUrl = urlService.createShortUrl(url.getOriginalUrl());
        return ResponseEntity.ok(newUrl);
    }

    @GetMapping("/{shortUrl}")
    @Operation(summary = "Get Original URL", description = "Generate a short code for original URL")
    public ResponseEntity<UrlDto> getOriginalUrl(@PathVariable String shortUrl) {
        UrlDto originalUrl = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.ok(originalUrl);
    }

    @GetMapping
    public ResponseEntity<List<UrlDto>> getAllOriginalUrl() {
        List<UrlDto> urls = urlService.getAllUrls();
        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrlByShortCode(@PathVariable String shortCode) {
        this.urlService.deleteUrlByShortCode(shortCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlDto> updateUrlByShortCode(@PathVariable String shortCode, @RequestBody Url newUrl) {
        return ResponseEntity.ok(urlService.updateUrlByShortCode(newUrl, shortCode));
    }
}
