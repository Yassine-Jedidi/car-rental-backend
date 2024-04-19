package com.miniprojet.backend.controller;

import com.miniprojet.backend.entities.User;
import com.miniprojet.backend.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint pour enregistrer un nouveau utilisateur
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        } else {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        }
    }

    // Endpoint pour récupérer tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public boolean validateLogin(@RequestBody UserController.LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return userService.validateLogin(email, password);
    }

    // Define LoginRequest class to capture login request payload
    @Data
    static class LoginRequest {
        private String email;
        private String password;

        // Getters and setters for email and password
        // Constructors
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> userOptional = userService.findUserByEmail(email);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    public Optional<User> findUserByID(@PathVariable String id) {
        return userService.findUserByID(id);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.patchUser(id,user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }
}
