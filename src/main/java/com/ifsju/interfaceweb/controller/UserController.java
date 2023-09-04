package com.ifsju.interfaceweb.controller;

import com.ifsju.interfaceweb.dto.UserDTO;
import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<UserDTO> getUserFindById(@PathVariable Long id){
        LOGGER.info("[findById] "+id);
        return ResponseEntity.ok(userService.getUserFindById(id));
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<UserDTO> getUserFindByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.getUserFindByEmail(email);
        if (userDTO != null){
            LOGGER.info("[findByEmail] "+email);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            LOGGER.info("[findByEmail] Not Found "+email);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
