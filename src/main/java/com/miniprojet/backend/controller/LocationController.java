package com.miniprojet.backend.controller;

import com.miniprojet.backend.entities.Location;
import com.miniprojet.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // Endpoint pour enregistrer une nouvelle opération de location
    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @GetMapping("/{id}") // Define the path variable in the annotation
    public Location getLocationById(@PathVariable String id) { // Change method name to getLocationById
        return locationService.getLocationById(id);
    }

    // Endpoint pour récupérer toutes les opérations de location
    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PutMapping("/{id}") // Mapping to handle PUT request
    public Location updateLocation(@PathVariable String id, @RequestBody Location updatedLocation) {
        return locationService.updateLocation(id, updatedLocation);
    }
    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable String id) {
        locationService.deleteLocation(id);
    }
    @GetMapping("/user/{userId}")
    public List<Location> getAllLocationsByUserId(@PathVariable String userId) {
        return locationService.getAllLocationsByUserId(userId);
    }

    @PatchMapping("/{id}/status")
    public Location updateLocationBookingStatus(@PathVariable String id, @RequestParam String status) {
        Location updatedLocation = locationService.updateLocationBookingStatus(id, status);
        return updatedLocation;
    }
}
