package com.ifsju.interfaceweb.controller;

import com.ifsju.interfaceweb.dto.UserDTO;
import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.service.EmailService;
import com.ifsju.interfaceweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*@PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }*/

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<UserDTO> getUserFindById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserFindById(id));
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<UserDTO> getUserFindByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserFindByEmail(email));
    }

}
