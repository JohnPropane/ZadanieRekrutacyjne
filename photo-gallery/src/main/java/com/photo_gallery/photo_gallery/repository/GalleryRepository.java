package com.photo_gallery.photo_gallery.repository;

import com.photo_gallery.photo_gallery.dto.Gallery;
import com.photo_gallery.photo_gallery.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GalleryRepository
        extends JpaRepository<Gallery, Long> {
    List<Gallery> findByOwner(Users owner);
}
