package com.netcracker.butrik.webstore.controller;


import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/findByEmail")
    public User findByEmail(@RequestParam String email) {
        return userJpaRepository.findByEmail(email);
    }

    @GetMapping(value = "/{email}")
    public User findByEmailPath(@PathVariable String email) {
        return userJpaRepository.findByEmail(email);
    }

    @PostMapping(value = "/load")
    public Optional<User> loadUser(@RequestBody @Valid User user) {
        userJpaRepository.save(user);
        return userJpaRepository.findById(user.getId());
    }


}
