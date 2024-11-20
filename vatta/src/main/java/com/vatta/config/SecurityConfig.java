package com.vatta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
            .authorizeHttpRequests(authorize -> authorize
                // Permitir acceso a todas las rutas sin autenticación
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .disable() // Deshabilitar el formulario de inicio de sesión
            )
            .logout(logout -> logout
                .disable() // Deshabilitar el cierre de sesión
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Puedes mantener esto si aún planeas usarlo en otros lugares
    }
}