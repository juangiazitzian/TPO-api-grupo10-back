package com.example.uade.tpo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.demo.auth.AuthenticationRequest;
import com.example.uade.tpo.demo.auth.AuthenticationResponse;
import com.example.uade.tpo.demo.auth.RegisterRequest;
import com.example.uade.tpo.demo.config.JwtService;
import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.repository.CuentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final CuentaRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        @Autowired
        CarritoService carritoService;

        public AuthenticationResponse register(RegisterRequest request) {
                
                Long carrito = carritoService.newCarrito();
                Cuenta user = new Cuenta((request.getName()),
                (request.getLastname()),
                (request.getUsername()),
                (passwordEncoder.encode(request.getPassword())), carrito,
                (request.getRole()));
                                
                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));

                var user = repository.findByUsername(request.getUsername())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}