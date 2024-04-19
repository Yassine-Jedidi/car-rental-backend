package com.miniprojet.backend.service;

import com.miniprojet.backend.entities.User;
import com.miniprojet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour enregistrer un nouvel utilisateur
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Méthode pour récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean validateLogin(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Check if the password is not null and matches the provided password
            return user.getPassword() != null && user.getPassword().equals(password);
        }
        return false; // If no user with the provided email is found
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findUserByID(String id) {
        return userRepository.findById(id);
    }

    public User patchUser(String id,User userUpdates) {
        Optional<User> existingUserOptional = userRepository.findUserById(id);
        User existingUser = existingUserOptional.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Apply partial updates
        if (userUpdates.getNom() != null) {
            existingUser.setNom(userUpdates.getNom());
        }
        if (userUpdates.getPrenom() != null) {
            existingUser.setPrenom(userUpdates.getPrenom());
        }
        if (userUpdates.getAdresse() != null) {
            existingUser.setAdresse(userUpdates.getAdresse());
        }
        if (userUpdates.getEmail() != null) {
            existingUser.setEmail(userUpdates.getEmail());
        }
        if (userUpdates.getPassword() != null) {
            existingUser.setPassword(userUpdates.getPassword());
        }
        if (userUpdates.getRole() != null) {
            existingUser.setRole(userUpdates.getRole());
        }

        // Save the updated user
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}

