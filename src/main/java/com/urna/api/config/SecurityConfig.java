package com.urna.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita a proteção CSRF. Essencial para APIs REST que recebem POST via Insomnia/Postman.
                .csrf(csrf -> csrf.disable())

                // 2. Configura as regras de autorização das rotas
                .authorizeHttpRequests(auth -> auth
                        // Substitua "/usuarios" pela rota real do seu Controller de cadastro
                        .requestMatchers("/api/usuarios").permitAll()

                        // Exige autenticação para qualquer outra rota
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}