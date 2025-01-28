package com.tecnologiadevalor.nanourl.controller;

import com.tecnologiadevalor.nanourl.dto.UrlDto;
import com.tecnologiadevalor.nanourl.model.Statistic;
import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/url")
@Tag(
        name = "URL Controller",
        description = "Controller responsible for URL shortening and management. " +
                "Provides endpoints for generating short URLs, retrieving original URLs, " +
                "and performing CRUD operations on stored URLs."
)
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    @Operation(
            summary = "Shorten URL",
            description = "Generates a unique short code for a given original URL. " +
                    "Accepts the original URL in the request body and returns a shortened URL representation."
    )
    public ResponseEntity<UrlDto> shortenUrl(@RequestBody Url url) {
        UrlDto newUrl = urlService.createShortUrl(url.getOriginalUrl());
        return ResponseEntity.ok(newUrl);
    }

    @GetMapping("/{shortCode}")
    @Operation(
            summary = "Retrieve Original URL",
            description = "Fetches the original URL corresponding to the provided short code. " +
                    "Returns the URL details if the short code is valid."
    )
    public ResponseEntity<UrlDto> getOriginalUrl(@PathVariable String shortCode) {
        UrlDto originalUrl = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.ok(originalUrl);
    }

    @GetMapping
    @Operation(
            summary = "List All URLs",
            description = "Retrieves all original URLs and their corresponding short codes stored in the database."
    )
    public ResponseEntity<List<UrlDto>> getAllOriginalUrl() {
        List<UrlDto> urls = urlService.getAllUrls();
        return ResponseEntity.ok(urls);
    }

    @DeleteMapping("/{shortCode}")
    @Operation(
            summary = "Delete URL",
            description = "Deletes a shortened URL from the database using the provided short code. " +
                    "Returns an HTTP 200 status on successful deletion."
    )
    public ResponseEntity<Void> deleteUrlByShortCode(@PathVariable String shortCode) {
        this.urlService.deleteUrlByShortCode(shortCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{shortCode}")
    @Operation(
            summary = "Update URL",
            description = "Updates the original URL associated with a given short code. " +
                    "Accepts the new URL in the request body and the short code as a path variable."
    )
    public ResponseEntity<UrlDto> updateUrlByShortCode(@PathVariable String shortCode, @RequestBody Url newUrl) {
        return ResponseEntity.ok(urlService.updateUrlByShortCode(newUrl, shortCode));
    }

    @GetMapping("/stats")
    @Operation(
            summary = "Retrieve URL Statistics",
            description = "Provides statistical information about the URL shortening service, " +
                    "such as the total number of URLs, total access count, and other related metrics.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Statistics retrieved successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Statistic.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error."
                    )
            },
            tags = {"Statistics"}
    )
    public ResponseEntity<Statistic> getStats() {
        return ResponseEntity.ok(urlService.getStats());
    }
}
