package com.tecnologiadevalor.nanourl;

import com.tecnologiadevalor.nanourl.dto.UrlDto;
import com.tecnologiadevalor.nanourl.model.Url;
import com.tecnologiadevalor.nanourl.repository.UrlRepository;
import com.tecnologiadevalor.nanourl.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = "app.base-url=https://nano.url")
public class UrlServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    @Spy
    private UrlService urlService;

    @Value("${app.base-url}")
    private String baseUrl;

    @Test
    public void generateShortCode_ShouldGenerateValidShortCode() {
        // Arrange
        // Simula que nenhum shortCode gerado já existe no repository.
        Mockito.when(urlRepository.existsByShortCode(Mockito.anyString())).thenReturn(false);

        // Act
        String shortCode = urlService.generateShortCode();

        // Assert
        assertEquals(6, shortCode.length());
        assertNotNull(shortCode, "The shortCode must not be null.");
    }

    @Test
    public void generateShortCode_ShouldRetryWhenShortCodeExists() {
        // Simula que o primeiro código já exista no banco, mas o segundo não
        Mockito.when(urlRepository.existsByShortCode(Mockito.anyString()))
                .thenReturn(true)
                .thenReturn(false);

        String shortCode = urlService.generateShortCode();

        assertNotNull(shortCode, "The generated shortCode must not be null.");
        assertEquals(6, shortCode.length(), "The shortCode must be 6 characters long.");

        // Verifica que o metodo existsByShortCode foi chamado pelo menos duas vezes
        Mockito.verify(urlRepository, Mockito.atLeast(2)).existsByShortCode(Mockito.anyString());
    }

    @Test
    public void createShortUrl_ShouldCreateAndSaveUrlSuccessfully() {
        /* Arrrange */
        String originalUrl = "https://github.com/jobson-batista/NanoURL";
        String shortCode = "AbC123";
        String shortUrl = baseUrl + "/" + shortCode;

        // Simulate method urlRepository
        Mockito.when(urlRepository.save(Mockito.any(Url.class))).thenAnswer(arg -> {
            Url url = arg.getArgument(0);
            url.setId("1");
            return url;
        });

        // Returns the 'shortCode' value when 'genetateShortCode()' method is called
        Mockito.doReturn(shortCode).when(urlService).generateShortCode();

        /* Act */
        UrlDto result = urlService.createShortUrl(originalUrl);

        /* Assert */
        assertNotNull(result);
        assertEquals(originalUrl, result.getOriginalUrl());
        assertEquals(shortCode, result.getShortCode());
        assertEquals(shortUrl, result.getShortUrl());
        assertNotNull(result.getExpiresAt());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        assertEquals(0, result.getAccessCount());
        assertFalse(result.isDeleted());

        // Verify that the repository was called
        Mockito.verify(urlRepository, Mockito.times(1)).save(Mockito.any(Url.class));
    }

}
