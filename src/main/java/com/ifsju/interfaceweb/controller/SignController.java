package com.ifsju.interfaceweb.controller;

import com.ifsju.interfaceweb.entity.User;
import com.ifsju.interfaceweb.service.JwtTokenService;
import com.ifsju.interfaceweb.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sign")
public class SignController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);

    public SignController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @Tag(name = "sign-up")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        LOGGER.info("[registerUser] " + user.getEmail() + " 계정 등록");

        return ResponseEntity.ok(userService.registerUser(user));
    }

    @Tag(name = "sign-up")
    @GetMapping("/check-duplicate/{email}")
    public ResponseEntity<Boolean> checkDuplicateUser(@PathVariable String email) {
        LOGGER.info("[checkDuplicate] " + email + " 중복 확인");
        if (userService.isDuplicate(email)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @Tag(name = "sign-in")
    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody User user) {
        if (userService.checkPassword(user)) {
            LOGGER.info("[signIn] " + user.getEmail() + " 로그인 성공");
            return ResponseEntity.ok(jwtTokenService.createToken(user.getEmail()));
        } else {
            LOGGER.info("[signIn] " + user.getEmail() + " 로그인 실패");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
