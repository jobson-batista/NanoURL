package com.tecnologiadevalor.nanourl.service;

import com.tecnologiadevalor.nanourl.exception.NotFoundException;
import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final String notFoundMessage = "Check the Short Code.";

    public Url createShortUrl(String originalUrl) {
        Url url = new Url();
        String shortCode = generateShortCode();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setShortUrl(buildShortUrl(shortCode));
        url.setExpiresAt(LocalDateTime.now().plusDays(30));
        url.setCreatedAt(LocalDateTime.now());
        url.setUpdatedAt(LocalDateTime.now());
        url.setAccessCount(0);
        return urlRepository.save(url);
    }

    public Url getOriginalUrl(String ShortUrl) throws NotFoundException {
        Url url = urlRepository.findByShortCode(ShortUrl).orElse(null);
        if(url != null) {
            url.incrementAccessCount();
        } else {
            throw new NotFoundException(notFoundMessage);
        }
        return urlRepository.save(url);
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

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    public void deleteUrlByShortCode(String shortCode) {
        Optional<Url> url = urlRepository.findByShortCode(shortCode);
        if(url.isEmpty()) throw new NotFoundException(notFoundMessage);
        this.urlRepository.deleteByShortCode(shortCode);
    }

    public Url updateUrlByShortCode(Url newUrl, String shortCode) {
        Optional<Url> url = urlRepository.findByShortCode(shortCode);
        if(url.isEmpty()) throw new NotFoundException(notFoundMessage);
        url.get().setOriginalUrl(newUrl.getOriginalUrl());
        url.get().setShortCode(newUrl.getShortCode());
        url.get().setShortUrl(buildShortUrl(newUrl.getShortCode()));
        url.get().setUpdatedAt(LocalDateTime.now());
        url.get().setExpiresAt(LocalDateTime.now().plusDays(30));
        return urlRepository.save(url.get());
    }

    private String buildShortUrl(String shortCode) {
        return baseUrl + contextPath + "/url/" + shortCode;
    }

}
