package com.tecnologiadevalor.nanourl.repository;

import com.tecnologiadevalor.nanourl.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, String> {

}
