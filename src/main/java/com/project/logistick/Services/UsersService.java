package com.project.logistick.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.logistick.Repositories.UsersRepository;
import com.project.logistick.Entitiesclasses.Users;

@Service
public class UsersService {
    @Autowired
    private UsersRepository userRepository;

    public Users registerUser(Users user) {
        // Always hash passwords before saving in production!
        return userRepository.save(user);
    }

    public Users loginUser(String email, String password) {
        Users user = userRepository.findByEmail(email);
        // Compare hashed passwords in production!
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
