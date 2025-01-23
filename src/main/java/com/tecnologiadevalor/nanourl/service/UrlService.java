package com.tecnologiadevalor.nanourl.service;

import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Url createShortUrl(String originalUrl) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(generateShortUrl(originalUrl));
        url.setExpiresAt(LocalDateTime.now().plusDays(30).toString());
        url.setCreatedAt(LocalDateTime.now());
        url.setUpdatedAt(LocalDateTime.now());
        url.setAccessCount(0);
        return urlRepository.save(url);
    }

    public Url getOriginalUrl(String ShortUrl) {
        return urlRepository.findById(ShortUrl).orElse(null);
    }

    public String generateShortUrl(String originalUrl) {
        return "";
    }

}
