package com.learning.user.services.controller;

import com.learning.user.services.dto.UserDetails;
import com.learning.user.services.dto.ResponseTemplate;
import com.learning.user.services.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController implements UserAPI {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserDetails user){
        log.info("Controller:: create new user");
        return ok().body(userService.createUser(user));
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchUser(@RequestBody UserDetails user){
        log.info("Controller:: search particular user details");
        return ok().body(userService.searchUser(user));
    }

    @GetMapping(value = "/fetchall")
    public ResponseEntity<?> fetchAllUsers(){
        log.info("Controller:: fetch all user details");
        return ok().body(userService.fetchAll());
    }

    @GetMapping(value = "/{userid}")
    public ResponseTemplate getUserDetailsById(@PathVariable("userid") long userId){
        log.info("Controller::" +
                "fetch the all information of an user with uploaded documents and department details ::::");
        return userService.findByUserDetails (userId);
    }

}
