package com.example.jwttest.service;

import com.example.jwttest.config.JwtService;
import com.example.jwttest.dto.AuthRequest;
import com.example.jwttest.dto.AuthResponse;
import com.example.jwttest.dto.SignupRequest;
import com.example.jwttest.model.Role;
import com.example.jwttest.model.UserModel;
import com.example.jwttest.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public String checkRegister(SignupRequest request) {
        if(userRepo.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already taken");
        }

        UserModel user = new UserModel();
        user.setLastName(request.lastName());
        user.setEmail(request.email());


        String encodedPassword = passwordEncoder.encode(request.password());
        user.setPassword(encodedPassword);


        user.setRole(Role.USER);


        userRepo.save(user);

        return "User registered successfully!";



    }
    public AuthResponse login(AuthRequest request) {
        UserModel user = userRepo.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, "Login Successful");
    }

}
