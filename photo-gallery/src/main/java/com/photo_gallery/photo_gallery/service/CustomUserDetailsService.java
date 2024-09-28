package com.photo_gallery.photo_gallery.service;

import com.photo_gallery.photo_gallery.dto.Users;
import com.photo_gallery.photo_gallery.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // Find the user by their login, throw exception if not found
        Users user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("Found user: " + user.getLogin() + " with role: " + user.getRole());

        // Create a GrantedAuthority object based on the user's role
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());

        // Build and return a UserDetails object with login, password, and authority
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .authorities(Collections.singletonList(authority))
                .build();
    }
}
