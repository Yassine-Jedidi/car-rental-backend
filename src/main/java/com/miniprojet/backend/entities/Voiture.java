package com.miniprojet.backend.entities;

import com.miniprojet.backend.service.VoitureService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "voitures")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voiture {
    @Id
    private String id;
    private String immatriculation;
    private String marque;
    private String dateMiseEnCirculation;
    private double prixLocation;
    private String picture;
    private User user;
}
