package com.miniprojet.backend.service;

import com.miniprojet.backend.entities.Voiture;
import com.miniprojet.backend.repository.VoitureRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VoitureService {
    private final VoitureRepository voitureRepository;

    @Autowired
    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }


    //CRUD
    public Voiture addVoiture(Voiture voiture){
        return voitureRepository.save(voiture);
    }

    public List<Voiture> getAllVoitures(){
        return voitureRepository.findAll();
    }

    public Voiture getVoitureById(String id){
        return voitureRepository.findById(id).get();
    }
    public void deleteVoitureById(String id){
        voitureRepository.deleteById(id);
    }

    public Voiture updateVoiture(String id, Voiture voitureDetails) {
        Voiture existingVoiture = voitureRepository.getVoitureById(id);
        if (existingVoiture == null) {
            return null;
        }
        existingVoiture.getUser().setNom(voitureDetails.getUser().getNom());
        existingVoiture.getUser().setPrenom(voitureDetails.getUser().getPrenom());
        existingVoiture.setImmatriculation(voitureDetails.getImmatriculation());
        existingVoiture.setMarque(voitureDetails.getMarque());
        existingVoiture.setDateMiseEnCirculation(voitureDetails.getDateMiseEnCirculation());
        existingVoiture.setPrixLocation(voitureDetails.getPrixLocation());
        existingVoiture.setPicture(voitureDetails.getPicture());

        Voiture updatedVoiture = voitureRepository.save(existingVoiture);
        return updatedVoiture;
    }
}