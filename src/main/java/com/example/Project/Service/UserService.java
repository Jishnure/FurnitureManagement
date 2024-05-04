package com.example.Project.Service;

import com.example.Project.Entiry.User;
import com.example.Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user){
        userRepository.save(user);
    }
//    public Optional<User> getUserById(Long id){
//        return userRepository.findById(id);
//    }
//    public User updateUser(User user){
//        return userRepository.save(user);
//    }


}
