package com.photo_gallery.photo_gallery.service;

import com.photo_gallery.photo_gallery.dto.Users;
import com.photo_gallery.photo_gallery.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<Users> login(String login, String password){
        return userRepository.findByLoginAndPassword(login, password);
    }

    public Optional<Users> findById(Long id){
        return userRepository.findById(id);
    }

    public Optional<Users> findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public List<Users> findAllByRole(String role){
        return userRepository.findByRole(role);
    }

    public List<Users> showUsers(){
        return userRepository.findAll();
    }

    public Users addUser(String login, String password, String role){
        if(userRepository.findByLogin(login).isPresent()){
            throw new IllegalArgumentException("User with this login already exists");
        }
        String encodedPassword = passwordEncoder.encode(password);
        Users user = new Users();
        user.setLogin(login);
        user.setPassword(encodedPassword);
        user.setRole(role);

        return userRepository.save(user);
    }
}
