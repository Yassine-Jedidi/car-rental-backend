package com.miniprojet.backend.repository;

import com.miniprojet.backend.entities.Voiture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoitureRepository extends MongoRepository<Voiture,String> {
    Voiture getVoitureById(String id);
}
