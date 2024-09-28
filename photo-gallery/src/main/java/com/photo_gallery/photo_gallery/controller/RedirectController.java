package com.photo_gallery.photo_gallery.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    //Method that redirect user after login to correct site based on its role
    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {
        System.out.println("Authorities: " + authentication.getAuthorities());

        // Check if the user has the ROLE_ADMIN authority
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin-page";

        // Check if the user has the ROLE_USER authority
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return "redirect:/user-page";
        }
        return "redirect:/";
    }
}
