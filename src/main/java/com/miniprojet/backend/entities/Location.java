package com.miniprojet.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "locations")
public class Location {
    @Id
    private String id;
    private String dateLocation;
    private String dateDebut;
    private String dateFin;
    private double fraisLocation;
    private String modePaiement;
    private double montantGarantie;
    private double montantTotal;
    private String status;
    private Voiture voiture;
    private User user;

}
