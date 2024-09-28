package com.photo_gallery.photo_gallery.controller;

import com.photo_gallery.photo_gallery.dto.Gallery;
import com.photo_gallery.photo_gallery.dto.Photo;
import com.photo_gallery.photo_gallery.service.GalleryService;
import com.photo_gallery.photo_gallery.service.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class PhotoController {

    private final PhotoService photoService;
    private final GalleryService galleryService;

    public PhotoController(PhotoService photoService, GalleryService galleryService) {
        this.photoService = photoService;
        this.galleryService = galleryService;
    }

    //POST request that add a new photo to a gallery
    @PostMapping("/add-photo")
    public String addPhoto(@RequestParam("photoName") String photoName,
                           @RequestParam("galleryId") Long galleryId){
        //Find gallery by its ID
        Optional<Gallery> gallery = galleryService.findById(galleryId);

        //Checking if gallery exists
        if(gallery.isPresent()){
            Gallery owner = gallery.get();

            Photo photo = new Photo();
            photo.setName(photoName);
            photo.setGallery(owner);

            photoService.addPhoto(photo);
        }

        return "redirect:/admin-page";
    }

    //GET request that shows all photos
    @GetMapping("/photos")
    @ResponseBody
    public List<Photo> showPhotos(){
        return photoService.findAllPhotos();
    }

}
