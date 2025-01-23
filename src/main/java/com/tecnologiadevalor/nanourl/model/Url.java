package com.tecnologiadevalor.nanourl.model;

import com.tecnologiadevalor.nanourl.entity.BaseEntity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "urls")
public class Url extends BaseEntity {

    @Id
    private String id;

    private String originalUrl;
    private String shortUrl;
    private String expiresAt;
    private int accessCount;


}
