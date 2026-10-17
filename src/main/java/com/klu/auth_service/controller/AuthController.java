
package com.klu.auth_service.controller;
import com.klu.auth_service.JwtService;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody Map<String, String> data) {

        String username = data.get("username");
        String password = data.get("password");

        if ("admin".equals(username)
                && "password123".equals(password)) {

            String token = jwtService.generateToken(username);

            return Map.of(
                "message", "Login successful",
                "token", token
            );
        }

        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "Invalid username or password"
        );
    }
}
