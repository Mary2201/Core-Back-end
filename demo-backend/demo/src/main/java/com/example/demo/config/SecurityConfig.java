package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desactiva CSRF para pruebas (reactívalo en producción)
            .cors().and()
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/employee/save", "/api/v1/employee/login").permitAll() // Permite acceso sin autenticación
            .anyRequest().authenticated() // Protege otros endpoints
            .and()
            .formLogin().disable() // Opcional: desactiva el login por formulario
            .httpBasic().disable(); // Opcional: desactiva autenticación básica
        return http.build();
    }*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors().and()
            .authorizeHttpRequests().anyRequest().permitAll(); // Permite todas las solicitudes
        return http.build();
    }

}
