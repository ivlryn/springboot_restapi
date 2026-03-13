package com.example.jwttest.controller;

import com.example.jwttest.model.UserModel;
import com.example.jwttest.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;


    @GetMapping("/me")
    public ResponseEntity<UserModel> getMe(@AuthenticationPrincipal UserModel user) {
        return ResponseEntity.ok(user);
    }


    @GetMapping("/admin/all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userRepo.findAll());
    }
}