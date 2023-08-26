package com.ifsju.interfaceweb.service;

import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User getUserById(Long id){
        return userRepository.findById(id);
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User updateUser(Long id, User user){
        if(userRepository.existsById(id)){
            return userRepository.save(user);
        }else{
            return null;
        }
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
