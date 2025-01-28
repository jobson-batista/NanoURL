package com.tecnologiadevalor.nanourl;

import com.tecnologiadevalor.nanourl.service.UrlService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UrlServiceTest {

    private final UrlService urlService = new UrlService();

    @Test
    public void generateShortCodeTest() {

        String shortCode = urlService.generateShortCode();

        assertEquals(6, shortCode.length());
        assertNotNull(shortCode, "The shortCode must not be null.");
    }
}
