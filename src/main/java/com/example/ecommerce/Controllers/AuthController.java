package com.example.ecommerce.Controllers;

import com.example.ecommerce.Auth.AuthRequest;
import com.example.ecommerce.Auth.AuthResponse;
import com.example.ecommerce.Services.JwtService;
import com.example.ecommerce.modele._User;
import com.example.ecommerce.repositories._UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final _UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        _User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody _User user) {
        // 1. Vérifier si l'email est vide ou invalide
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", "Email requis.")
            );
        }

        // 2. Vérifier si l'email existe déjà en base
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("message", "Email déjà utilisé.")
            );
        }

        // 3. Encoder le mot de passe et enregistrer l'utilisateur
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        _User savedUser = userRepository.save(user);

        // 4. Retourner une réponse 200 OK
        return ResponseEntity.ok(
                Map.of("message", "Utilisateur enregistré avec succès.", "userId", savedUser.getId())
        );
    }



}
