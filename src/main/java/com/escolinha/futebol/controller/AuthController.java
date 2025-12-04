package com.escolinha.futebol.controller;

import com.escolinha.futebol.dto.LoginRequestDTO;
import com.escolinha.futebol.dto.LoginResponseDTO;
import com.escolinha.futebol.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request){
        return authService.login(request);
    }
}
