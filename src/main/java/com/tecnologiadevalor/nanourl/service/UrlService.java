package com.tecnologiadevalor.nanourl.service;

import com.tecnologiadevalor.nanourl.dto.UrlDto;
import com.tecnologiadevalor.nanourl.exception.NotFoundException;
import com.tecnologiadevalor.nanourl.exception.ShortCodeLengthException;
import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    private final String notFoundMessage = "Check the Short Code.";

    public UrlDto createShortUrl(String originalUrl) {
        Url url = new Url();
        String shortCode = generateShortCode();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        url.setShortUrl(buildShortUrl(shortCode));
        url.setExpiresAt(LocalDateTime.now().plusDays(30));
        url.setCreatedAt(LocalDateTime.now());
        url.setUpdatedAt(LocalDateTime.now());
        url.setAccessCount(0);
        return urlRepository.save(url).toDto();
    }

    public UrlDto getOriginalUrl(String ShortCode) throws NotFoundException {
        Url url = urlRepository.findByShortCode(ShortCode).orElse(null);
        if(url != null) {
            url.incrementAccessCount();
        } else {
            throw new NotFoundException(notFoundMessage);
        }
        return urlRepository.save(url).toDto();
    }

    public String generateShortCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int codeLength = 6;
        StringBuilder shortCode = new StringBuilder(codeLength);
        Random random = new Random();

        do {
            for (int j = 0; j < codeLength; j++) {
                shortCode.append(characters.charAt(random.nextInt(characters.length())));
            }
        } while(urlRepository.existsByShortCode(shortCode.toString()));

        return shortCode.toString();
    }

    public List<UrlDto> getAllUrls() {
        List<UrlDto> dtos = new ArrayList<>();
        for(Url url: urlRepository.findAll()) {
            dtos.add(url.toDto());
        }
        return dtos;
    }

    public void deleteUrlByShortCode(String shortCode) {
        Optional<Url> url = urlRepository.findByShortCode(shortCode);
        if(url.isEmpty()) throw new NotFoundException(notFoundMessage);
        this.urlRepository.deleteByShortCode(shortCode);
    }

    public UrlDto updateUrlByShortCode(Url newUrl, String shortCode) {
        Optional<Url> url = urlRepository.findByShortCode(shortCode);
        if(url.isEmpty()) throw new NotFoundException(notFoundMessage);
        if(newUrl.getOriginalUrl() != null) {
            url.get().setOriginalUrl(newUrl.getOriginalUrl());
        }
        if(newUrl.getShortCode().length() != 6) {
            throw new ShortCodeLengthException();
        }
        url.get().setShortCode(newUrl.getShortCode());
        if(newUrl.getShortCode() != null) {
            url.get().setShortUrl(buildShortUrl(newUrl.getShortCode()));
        }
        url.get().setUpdatedAt(LocalDateTime.now());
        url.get().setExpiresAt(LocalDateTime.now().plusDays(30));
        return urlRepository.save(url.get()).toDto();
    }

    private String buildShortUrl(String shortCode) {
        return baseUrl + "/" + shortCode;
    }

}
