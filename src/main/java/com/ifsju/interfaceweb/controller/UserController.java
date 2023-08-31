package com.ifsju.interfaceweb.controller;

import com.ifsju.interfaceweb.dto.UserDTO;
import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.service.JwtTokenProvider;
import com.ifsju.interfaceweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        LOGGER.info("[registerUser] "+user.getEmail());

        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        log.info("[getAll]");

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<UserDTO> getUserFindById(@PathVariable Long id){
        LOGGER.info("[findById] "+id);
        return ResponseEntity.ok(userService.getUserFindById(id));
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<UserDTO> getUserFindByEmail(@PathVariable String email){
        LOGGER.info("[findByEmail] "+email);
        return ResponseEntity.ok(userService.getUserFindByEmail(email));
    }

    @GetMapping("/token/create/{email}")
    public String token(@PathVariable String email){
        return jwtTokenProvider.createToken(email);
    }

}
