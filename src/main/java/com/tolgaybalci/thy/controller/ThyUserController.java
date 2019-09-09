package com.tolgaybalci.thy.controller;

import com.tolgaybalci.thy.entity.ThyUser;
import com.tolgaybalci.thy.repository.ThyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class ThyUserController {

    @Autowired
    private ThyUserRepository thyUserRepository;

    @GetMapping
    public ResponseEntity<ThyUser> getyUserList(){
        return new ResponseEntity(thyUserRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ThyUser> addUser(@RequestBody ThyUser thyUser) {
        thyUserRepository.save(thyUser);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<ThyUser> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity(thyUserRepository.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("thy-login")
    public ResponseEntity<ThyUser> thyLogin(@RequestBody ThyUser thyUser) {
        ThyUser tempUser = thyUserRepository.findByUsernameOrEmail(thyUser.getUsername(), thyUser.getEmail());
        if (tempUser.getPassword().equals(thyUser.getPassword())) {
            return new ResponseEntity(tempUser, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<ThyUser> login(@RequestBody ThyUser thyUser) {
        ThyUser tempUser = thyUserRepository.login(thyUser.getUsername(), thyUser.getEmail(), thyUser.getPassword());
        if(tempUser != null ) {
            return new ResponseEntity(tempUser, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
    }

}
