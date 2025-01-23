package com.tecnologiadevalor.nanourl.controller;

import com.tecnologiadevalor.nanourl.dto.UrlDto;
import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlDto> shortenUrl(@RequestBody Url url) {
        UrlDto newUrl = urlService.createShortUrl(url.getOriginalUrl());
        return ResponseEntity.ok(newUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<UrlDto> getOriginalUrl(@PathVariable String shortUrl) throws Exception {
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
