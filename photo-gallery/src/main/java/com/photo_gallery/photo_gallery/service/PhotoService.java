package com.photo_gallery.photo_gallery.service;

import com.photo_gallery.photo_gallery.dto.Gallery;
import com.photo_gallery.photo_gallery.dto.Photo;
import com.photo_gallery.photo_gallery.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }
    public Photo addPhoto(Photo photo){
        return photoRepository.save(photo);
    }
    public List<Photo> findAllPhotos(){
        return photoRepository.findAll();
    }

    public List<Photo> findByGalleryId(Long galleryId) {
        return photoRepository.findByGalleryId(galleryId);
    }

}
