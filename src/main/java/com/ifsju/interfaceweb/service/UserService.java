package com.ifsju.interfaceweb.service;

import com.ifsju.interfaceweb.dto.UserDTO;
import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /*
    public User createUser(User user){
        return userRepository.save(user);
    }
    */
    public User registerUser(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<UserDTO> getAllUsers(){
        List<User> users =  userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getId(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserDTO getUserFindById(Long id){
        User user = userRepository.findById(id);
        return new UserDTO(user.getId(), user.getEmail());
    }

    public UserDTO getUserFindByEmail(String email){
        User user = userRepository.findByEmail(email);
        return new UserDTO(user.getId(), user.getEmail());
    }


}
