package com.photo_gallery.photo_gallery.controller;

import com.photo_gallery.photo_gallery.dto.Gallery;
import com.photo_gallery.photo_gallery.dto.Users;
import com.photo_gallery.photo_gallery.service.GalleryService;
import com.photo_gallery.photo_gallery.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {


    private final UserService userService;
    private final GalleryService galleryService;

    public UserController(UserService userService, GalleryService galleryService) {
        this.userService = userService;
        this.galleryService = galleryService;
    }

    // GET request handler for the login page
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    // GET request handler for the admin page
    @GetMapping("/admin-page")
    public String adminPage(Model model) {

        //Adding all users to the model
        List<Users> users = userService.findAllByRole("USER");
        model.addAttribute("users", users);

        //Adding all galleries to the model
        List<Gallery> galleries = galleryService.findAllGalleries();
        model.addAttribute("galleries", galleries);

        return "admin-page";
    }

    // GET request handler for the user page
    @GetMapping("/user-page")
    public String userPage(Model model, Principal principal) {

        // Get the currently authenticated user's login
        String login = principal.getName();

        // Find the user by its login name
        Optional<Users> user = userService.findByLogin(login);

        //Checking if user exists
        if(user.isPresent()){
            Users user1 = user.get();
            model.addAttribute("user", user1);

            //Add all user's galleries to the model
            List<Gallery> galleries = galleryService.findByUserId(user1.getId());
            model.addAttribute("galleries", galleries);
        }

        return "user-page";
    }

    //GET request that shows all users
    @GetMapping("/users")
    @ResponseBody
    public List<Users> showUsers(){
        return userService.showUsers();
    }

    record RegisterUserRequest(String login, String password){}

    //POST request that add a new user with USER role
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody RegisterUserRequest request){
        userService.addUser(request.login(), request.password(), "USER");
        return ResponseEntity.ok("User has been added");
    }

    //POST request that add a new user with ADMIN role
    @PostMapping("/register-admin")
    @ResponseBody
    public ResponseEntity<String> addAdmin(@RequestBody RegisterUserRequest request){
        userService.addUser(request.login(), request.password(), "ADMIN");
        return ResponseEntity.ok("Admin has been added");
    }
}
