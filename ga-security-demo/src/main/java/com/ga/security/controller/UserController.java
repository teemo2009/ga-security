package com.ga.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ga.security.domain.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello/{id:\\d+}")
    public String hello(@PathVariable Integer id){
        return "hello spring security"+id;
    }


    @GetMapping("/user/{id:\\d+}")
    @JsonView(User.DetailView.class)
    public User user(@PathVariable Integer id){
        User user=new User();
        user.setUsername("tom");
        user.setPassword(id+"");
        return user;
    }


}
