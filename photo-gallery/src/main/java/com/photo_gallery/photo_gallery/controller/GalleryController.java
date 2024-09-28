package com.photo_gallery.photo_gallery.controller;

import com.photo_gallery.photo_gallery.dto.Gallery;
import com.photo_gallery.photo_gallery.dto.Photo;
import com.photo_gallery.photo_gallery.dto.Users;
import com.photo_gallery.photo_gallery.service.GalleryService;
import com.photo_gallery.photo_gallery.service.PhotoService;
import com.photo_gallery.photo_gallery.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GalleryController {

    private final GalleryService galleryService;
    private final UserService userService;
    private final PhotoService photoService;

    public GalleryController(GalleryService galleryService, UserService userService, PhotoService photoService) {
        this.galleryService = galleryService;
        this.userService = userService;
        this.photoService = photoService;
    }

    //GET request that fetch a gallery by its ID
    @GetMapping("/gallery/{id}")
    public String getGallery(@PathVariable Long id, Model model){
        Optional<Gallery> gallery = galleryService.findById(id);

        //Checking if gallery exists
        if(gallery.isPresent()){
            List<Photo> photos = photoService.findByGalleryId(id);
            Gallery gallery1 = gallery.get();
            model.addAttribute("gallery", gallery1);
            model.addAttribute("photos", photos);
        }

        return "/user-gallery";
    }

    //POST request that add a new gallery to user
    @PostMapping("/add-gallery")
    public String addGallery(@RequestParam("galleryName") String galleryName,
                             @RequestParam("galleryUser") Long userId){

        //Finding user by its ID
        Optional<Users> user = userService.findById(userId);

        //Checking if user exists
        if(user.isPresent()){
            Users owner = user.get();

            Gallery gallery = new Gallery();
            gallery.setName(galleryName);
            gallery.setOwner(owner);

            galleryService.addGallery(gallery);
        }

        return "redirect:/admin-page";
    }

    //GET request that shows all galleries
    @GetMapping("/galleries")
    @ResponseBody
    public List<Gallery> showGalleries(){
        return galleryService.findAllGalleries();
    }
}
