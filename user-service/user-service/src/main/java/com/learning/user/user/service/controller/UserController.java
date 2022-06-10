package com.learning.user.user.service.controller;

import com.learning.user.user.service.VO.ResponseTemplateVO;
import com.learning.user.user.service.entity.UserEntity;
import com.learning.user.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    private static final String USER_SERVICE = "USER-SERVICE";

    @PostMapping("/")
    public UserEntity createUser(@RequestBody UserEntity user){
        log.info("save department controller Class::::");
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
   // @CircuitBreaker(name = USER_SERVICE ,fallbackMethod = "fallbackUserServiceMethod")
    public ResponseTemplateVO getUserwithDepartment(@PathVariable("id") Long userId){
        log.info("find by Id department controller Class::::");
        return userService.findByDepartmentId (userId);
    }
/*
    public ResponseTemplateVO fallbackUserServiceMethod(Exception e){
        ResponseTemplateVO vo = new ResponseTemplateVO();
        vo.setDepartment(null);
        vo.setUser(null);
        return vo;
    }*/
}
