package com.tecnologiadevalor.nanourl.repository;

import com.tecnologiadevalor.nanourl.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends MongoRepository<Url, String> {

    Optional<Url> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

    long countByExpiresAtAfter(LocalDateTime now);

    long countByExpiresAtBefore(LocalDateTime now);

    List<Url> findByDeletedFalse();
}
