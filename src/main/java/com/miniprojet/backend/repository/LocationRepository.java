package com.miniprojet.backend.repository;

import com.miniprojet.backend.entities.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocationRepository extends MongoRepository<Location,String> {
    List<Location> findByUserId(String userId);
}
