package com.photo_gallery.photo_gallery.dto;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Gallery {
    @Id
    @SequenceGenerator(
            name = "gallery_id_sequence",
            sequenceName = "gallery_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gallery_id_sequence"
    )
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users owner;

    public Gallery(){}

    public Gallery(Long id, String name, Users owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
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

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallery gallery = (Gallery) o;
        return Objects.equals(id, gallery.id) && Objects.equals(name, gallery.name) && Objects.equals(owner, gallery.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner);
    }
}
