package com.tecnologiadevalor.nanourl.model;

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

}
