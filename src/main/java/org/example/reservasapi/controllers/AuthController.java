package org.example.reservasapi.controllers;

import org.example.reservasapi.DTO.LoginRequestDTO;
import org.example.reservasapi.DTO.LoginResponseDTO;
import org.example.reservasapi.DTO.UserRegisterDTO;
import org.example.reservasapi.config.JwtTokenProvider;
import org.example.reservasapi.entities.UserEntity;
import org.example.reservasapi.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> save(@RequestBody UserRegisterDTO userDTO) {
        try {
            UserEntity userEntity = this.userRepository.save(
                    UserEntity.builder()
                            .username(userDTO.getUsername())
                            .password(passwordEncoder.encode(userDTO.getPassword()))
                            .email(userDTO.getEmail())
                            .authorities(List.of("ROLE_USER", "ROLE_ADMIN"))
                            .build());

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("email", userEntity.getEmail(),
                            "username", userEntity.getUsername())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Email or username already in use"));
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
            Authentication auth = authenticationManager.authenticate(userPassAuthToken);
            UserEntity user = (UserEntity) auth.getPrincipal();
            String token = this.tokenProvider.generateToken(auth);

            return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of(
                            "path", "/auth/login",
                            "message", "Invalid credentials",
                            "timestamp", new Date()
                    )
            );
        }
    }
}