package com.tecnologiadevalor.nanourl.service;

import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    public Url createShortUrl(String originalUrl) {
        Url url = new Url();
        String shortCode = generateShortCode();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setShortUrl(baseUrl + "/api/urls/" + shortCode);
        url.setExpiresAt(LocalDateTime.now().plusDays(30));
        url.setCreatedAt(LocalDateTime.now());
        url.setUpdatedAt(LocalDateTime.now());
        url.setAccessCount(0);
        return urlRepository.save(url);
    }

    public Url getOriginalUrl(String ShortUrl) {
        return urlRepository.findByShortCode(ShortUrl).orElse(null);
    }

    public String generateShortCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortCode = new StringBuilder();
        Random random = new Random();

        for(int k = 0; k < 6; k++) {
            shortCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortCode.toString();
    }

}
