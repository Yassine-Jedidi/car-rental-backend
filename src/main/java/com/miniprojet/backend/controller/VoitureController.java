package com.miniprojet.backend.controller;

import com.miniprojet.backend.entities.Voiture;
import com.miniprojet.backend.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/voitures")
@CrossOrigin(origins = "http://localhost:4200")
public class VoitureController {
    @Autowired
    private VoitureService voitureService;
    @PostMapping("/add")
    public Voiture addVoiture(@RequestBody Voiture voiture){
        return voitureService.addVoiture(voiture);
    }
    @GetMapping("/get")
    public List<Voiture> getAllVoitures(){
        return voitureService.getAllVoitures();
    }
    @GetMapping("/get/{id}")
    public Voiture getVoitureById(@PathVariable String id){
        return voitureService.getVoitureById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteVoitureById(@PathVariable String id){
        voitureService.deleteVoitureById(id);
    }

    @PutMapping("/modifier/{id}")
    public Voiture updateVoiture(@PathVariable String id, @RequestBody Voiture voitureDetails) {
        Voiture updatedVoiture = voitureService.updateVoiture(id,voitureDetails);
        return updatedVoiture;
    }
}