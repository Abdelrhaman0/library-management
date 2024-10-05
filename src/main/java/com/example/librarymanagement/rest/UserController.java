package com.example.librarymanagement.rest;

import com.example.librarymanagement.entity.User;
import com.example.librarymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUser(){
        return service.getAllUser();
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return service.getUser(id);
    }
}
