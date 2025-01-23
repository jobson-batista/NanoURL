package com.tecnologiadevalor.nanourl.controller;

import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestBody Url url) {
        Url newUrl = urlService.createShortUrl(url.getOriginalUrl());
        return ResponseEntity.ok(newUrl);
    }

    @GetMapping("{shortUrl}")
    public ResponseEntity<Url> getOriginalUrl(@PathVariable String shortUrl) throws Exception {
        Url originalUrl = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.ok(originalUrl);
    }

    @GetMapping
    public ResponseEntity<List<Url>> getAllOriginalUrl() {
        List<Url> urls = urlService.getAllUrls();
        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("{shortCode}")
    public ResponseEntity<Void> deleteUrlByShortCode(@PathVariable String shortCode) {
        this.urlService.deleteUrlByShortCode(shortCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{shortCode}")
    public ResponseEntity<Url> updateUrlByShortCode(@PathVariable String shortCode, @RequestBody Url newUrl) {
        return ResponseEntity.ok(urlService.updateUrlByShortCode(newUrl, shortCode));
    }
}
