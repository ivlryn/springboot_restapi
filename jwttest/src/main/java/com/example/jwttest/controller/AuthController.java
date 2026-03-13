package com.example.jwttest.controller;

import com.example.jwttest.dto.AuthRequest;
import com.example.jwttest.dto.AuthResponse;
import com.example.jwttest.dto.SignupRequest;
import com.example.jwttest.model.UserModel;
import com.example.jwttest.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.checkRegister(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    @GetMapping("/me")
    public ResponseEntity<UserModel> getCurrentUser(@AuthenticationPrincipal UserModel user) {
        return ResponseEntity.ok(user);
    }

}
