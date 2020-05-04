package com.netcracker.butrik.webstore.controller;


import com.netcracker.butrik.webstore.dto.UserDto;
import com.netcracker.butrik.webstore.dto.mapper.impl.UserMapperImpl;
import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private UserMapperImpl userMapper;

    @GetMapping(value = "/findAll")
    public List<UserDto> findAll() {
        List<User>users =userService.findAll();
        return userMapper.toDtos(users);
    }

    @GetMapping(value = "/findAll/orders")
    public List<UserDto> findAllWithOrders() {
        List<User>users =userService.findAll();
        return userMapper.toDtosWithOrders(users);
    }

    @GetMapping(value = "/findById")
    public UserDto findById(@RequestParam int id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }

    @GetMapping(value = "/findById/orders")
    public UserDto findByIdWithoutOrders(@RequestParam int id) {
        User user = userService.findById(id);
        return userMapper.toDtoWithOrders(user);
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
