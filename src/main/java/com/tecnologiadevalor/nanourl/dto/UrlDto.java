package com.tecnologiadevalor.nanourl.dto;

import com.tecnologiadevalor.nanourl.entity.BaseEntity;
import com.tecnologiadevalor.nanourl.model.Url;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto extends BaseEntity {

    private String originalUrl;
    private String shortCode;
    private String shortUrl;
    private LocalDateTime expiresAt;
    private int accessCount;
    private boolean deleted;

    public Url toUrl() {
        Url url = new Url();
        url.setOriginalUrl(this.originalUrl);
        url.setShortUrl(this.shortUrl);
        url.setShortCode(this.shortCode);
        url.setExpiresAt(this.expiresAt);
        url.setAccessCount(this.accessCount);
        url.setUpdatedAt(this.getUpdatedAt());
        url.setCreatedAt(this.getCreatedAt());
        url.setDeleted(this.isDeleted());
        return url;
    }

}
