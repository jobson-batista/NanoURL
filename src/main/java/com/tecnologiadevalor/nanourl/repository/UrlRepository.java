package com.tecnologiadevalor.nanourl.repository;

import com.tecnologiadevalor.nanourl.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<Url, String> {

    Optional<Url> findByShortCode(String shortUrl);
}
