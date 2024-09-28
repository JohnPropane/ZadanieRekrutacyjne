package com.photo_gallery.photo_gallery.service;

import com.photo_gallery.photo_gallery.dto.Gallery;
import com.photo_gallery.photo_gallery.dto.Users;
import com.photo_gallery.photo_gallery.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryService {
    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    public List<Gallery> findAllGalleries(){
        return galleryRepository.findAll();
    }

    public Gallery addGallery(Gallery gallery){
        return galleryRepository.save(gallery);
    }

    public Optional<Gallery> findById(Long id){
        return galleryRepository.findById(id);
    }

    public List<Gallery> findByUserId(Long userId){
        Users owner = new Users();
        owner.setId(userId);
        return galleryRepository.findByOwner(owner);
    }
}
