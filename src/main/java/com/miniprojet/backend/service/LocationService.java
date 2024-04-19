package com.miniprojet.backend.service;

import com.miniprojet.backend.entities.Location;
import com.miniprojet.backend.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Méthode pour enregistrer une nouvelle opération de location
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    // Méthode pour récupérer toutes les opérations de location
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }


    public Location getLocationById(String id) {
        return locationRepository.findById(id).get();
    }

    // Méthode pour mettre à jour une location
    public Location updateLocation(String id, Location updatedLocation) {
        // Vérifier si la location existe
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));

        // Mettre à jour les champs de la location existante avec les nouvelles valeurs
        existingLocation.setDateDebut(updatedLocation.getDateDebut());
        existingLocation.setDateFin(updatedLocation.getDateFin());
        existingLocation.setFraisLocation(updatedLocation.getFraisLocation());
        existingLocation.setMontantTotal(updatedLocation.getMontantTotal());
        existingLocation.setMontantGarantie(updatedLocation.getMontantGarantie());
        existingLocation.setModePaiement(updatedLocation.getModePaiement());
        // Enregistrer la location mise à jour dans la base de données
        return locationRepository.save(existingLocation);
    }

    public void deleteLocation(String id) {
        locationRepository.deleteById(id);
    }

    public List<Location> getAllLocationsByUserId(String userId) {
        return locationRepository.findByUserId(userId);
    }

    public Location updateLocationBookingStatus(String id, String status) {
        // Retrieve the location by its ID
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));

        // Update the booking status
        existingLocation.setStatus(status);

        // Save the updated location
        return locationRepository.save(existingLocation);
    }

}