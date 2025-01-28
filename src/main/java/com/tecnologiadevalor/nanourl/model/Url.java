package com.tecnologiadevalor.nanourl.model;

import com.tecnologiadevalor.nanourl.dto.UrlDto;
import com.tecnologiadevalor.nanourl.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "urls")
public class Url extends BaseEntity {

    @Id
    private String id;

    private String originalUrl;
    private String shortCode;
    private String shortUrl;
    private LocalDateTime expiresAt;
    private int accessCount;
    private boolean deleted;

    public void incrementAccessCount() {
        this.accessCount++;
    }

    public UrlDto toDto() {
        UrlDto dto = new UrlDto();
        dto.setOriginalUrl(this.originalUrl);
        dto.setShortCode(this.shortCode);
        dto.setShortUrl(this.shortUrl);
        dto.setExpiresAt(this.expiresAt);
        dto.setAccessCount(this.accessCount);
        dto.setUpdatedAt(this.getUpdatedAt());
        dto.setCreatedAt(this.getCreatedAt());
        return dto;
    }

}
