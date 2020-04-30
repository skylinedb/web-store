package com.netcracker.butrik.webstore.controller;


import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import com.netcracker.butrik.webstore.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${requestMapping.user}")
@CrossOrigin(origins = "${cors.frontend.url}")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/findById")
    public User findById(@RequestParam int id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/findByEmail")
    public User findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(value = "/{email}")
    public User findByEmailPath(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping(value = "/save")
    public User loadUser(@RequestBody @Valid User user) {
//        userJpaRepository.save(user);
        userService.save(user);
        return userService.findById(user.getId());
    }

    @PostMapping(value = "/update")
    public User updateUser(@RequestBody @Valid User user) {
        userService.update(user);
        return userService.findById(user.getId());
    }

    @PostMapping(value = "/delete")
    public void deleteUser(@RequestBody User user) {
        userService.delete(user);
    }
}
