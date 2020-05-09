package com.netcracker.butrik.webstore.controller;


import com.netcracker.butrik.webstore.dto.UserDto;
import com.netcracker.butrik.webstore.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/findAll")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/findAll/orders")
    public List<UserDto> findAllWithOrders() {
        return userService.findAll_withOrders();
    }

    @GetMapping(value = "/findById")
    public UserDto findById(@RequestParam int id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/findById/orders")
    public UserDto findByIdWithoutOrders(@RequestParam int id) {
        return userService.findById_withOrders(id);
    }

    @GetMapping(value = "/findByEmail")
    public UserDto findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(value = "/findByEmail/orders")
    public UserDto findByEmail_withOrders(@RequestParam String email) {
        return userService.findByEmail_withOrders(email);
    }

    @PostMapping(value = "/save")
    public UserDto loadUser(@RequestBody @Valid UserDto userDto) {
        userService.save(userDto);
        return userService.findById(userDto.getId());
    }

    @PostMapping(value = "/update")
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        userService.update(userDto);
        return userService.findById(userDto.getId());
    }

    @PostMapping(value = "/delete")
    public void deleteUser(@RequestBody UserDto userDto) {
        userService.delete(userDto);
    }
}
