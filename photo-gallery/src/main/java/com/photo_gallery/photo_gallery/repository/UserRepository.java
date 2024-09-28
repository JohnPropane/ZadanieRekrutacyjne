package com.photo_gallery.photo_gallery.repository;

import com.photo_gallery.photo_gallery.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByLoginAndPassword(String login, String password);
    Optional<Users> findByLogin(String login);
    List<Users> findByRole(String role);
}
