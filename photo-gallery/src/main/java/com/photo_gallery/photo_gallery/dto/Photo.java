package com.photo_gallery.photo_gallery.dto;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Photo {
    @Id
    @SequenceGenerator(
            name = "photo_id_sequence",
            sequenceName = "photo_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "photo_id_sequence"
    )
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "gallery_id", referencedColumnName = "id")
    private Gallery gallery;

    public Photo(){}

    public Photo(Long id, String name, Gallery gallery) {
        this.id = id;
        this.name = name;
        this.gallery = gallery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gallery=" + gallery +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) && Objects.equals(name, photo.name) && Objects.equals(gallery, photo.gallery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gallery);
    }
}
