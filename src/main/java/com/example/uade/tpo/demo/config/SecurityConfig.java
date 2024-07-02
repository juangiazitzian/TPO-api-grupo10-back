package com.example.uade.tpo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.example.uade.tpo.demo.entity.Role;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/auth/**").permitAll()
                                                .requestMatchers("/error/**").permitAll()
                                                .requestMatchers("/api/cuentas/id").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/cuentas/username").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/cuentas/update").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/cuentas/delete").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/carritos/id").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/descuentos/**").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/cuentas/delete").hasAnyAuthority(Role.ADMIN.name())

                                                .requestMatchers("/api/facturas/**").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/pedidos").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/pedidos").hasAnyAuthority(Role.ADMIN.name())
                                                
                                                .requestMatchers("/api/vinilos/add-vinilo").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/vinilos/update/{id}").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/vinilos/delete/{id}").hasAnyAuthority(Role.ADMIN.name())
                                                .anyRequest().permitAll()
                                                )
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
