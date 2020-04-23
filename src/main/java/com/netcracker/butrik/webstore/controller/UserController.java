package com.netcracker.butrik.webstore.controller;


import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @GetMapping(value = "/findById")
    public Optional<User> findById(@RequestParam int id) {
        return userJpaRepository.findById(id);
    }

    @PostMapping(value = "/load")
    public Optional<User> loadUser(@RequestBody User user) {
        userJpaRepository.save(user);
        return userJpaRepository.findById(user.getId());
    }


}
