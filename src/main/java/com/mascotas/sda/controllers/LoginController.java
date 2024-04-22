package com.mascotas.sda.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mascotas.sda.dto.auth.LoginRequest;
import com.mascotas.sda.dto.auth.LoginResponse;
import com.mascotas.sda.service.auth.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt) {
        boolean isTokenValid = loginService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest authenticationRequest) {
        
        LoginResponse rsp = loginService.login(authenticationRequest);
        return ResponseEntity.ok(rsp);
    }
}
