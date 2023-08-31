package com.ifsju.interfaceweb.service;

import com.ifsju.interfaceweb.dto.UserDTO;
import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User registerUser(User user){
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
        Optional<User> userOptional = userRepository.findByEmail(email);
        try{
            if (userOptional.isPresent()){
                User user = userOptional.get();
                return new UserDTO(user.getId(), user.getEmail());
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public boolean isDuplicate(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isEmpty();
    }

    public boolean checkPassword(User user){
        Optional<User> userOptional = userRepository.findByEmail(user.getPassword());
        if (userOptional.isPresent()){
            String pw = userOptional.get().getPassword();
            return bCryptPasswordEncoder.matches(user.getPassword(), pw);
        } else {
            return false;
        }
    }


}
