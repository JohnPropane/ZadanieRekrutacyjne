package com.photo_gallery.photo_gallery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Main security configuration for the application
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for POSTMAN tests
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        // Allow access to public endpoints
                        .requestMatchers("/login", "/register", "/register-admin", "/users", "/galleries", "/photos").permitAll()

                        // Endpoints restricted to users with the ADMIN role
                        .requestMatchers("/add-photo").hasRole("ADMIN")
                        .requestMatchers("/add-gallery").hasRole("ADMIN")
                        .requestMatchers("/admin-page").hasRole("ADMIN")

                        // Endpoint restricted to users with the USER role
                        .requestMatchers("/user-page").hasRole("USER")

                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")

                        // Default redirect after successful login
                        .defaultSuccessUrl("/default", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                );

        return http.build();
    }

    // Bean responsible for password encoding using the BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
