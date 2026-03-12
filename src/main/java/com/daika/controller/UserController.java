package com.daika.controller;

import com.daika.model.User;
import com.daika.repository.UserRepository;
import com.daika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    private  final UserService service;
    public UserController(UserRepository repo, UserService service) {
        this.repo = repo;
        this.service = service;
    }


    @PostMapping
    public User createUser(@RequestBody User user) {
        return repo.save(user);
    }
    @PostMapping("/w")
    public User createUserWithWallet(@RequestBody User u){
        service.createUserWithWallet(u);
        return u;
    }


    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Optional<User> getById(@PathVariable String id){
        Optional<User> u=repo.findById(id);
        return u;
    }
}