package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.repository.OrderJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderJpaRepository orderJpaRepository;
//    @Autowired
//    private OrderService orderService;

    @GetMapping(value = "/findAll")
    public List<Order> findAll() {
        return orderJpaRepository.findAll();
    }

    @GetMapping(value = "/findById")
    public Optional<Order> findById(@RequestParam int id) {
        return orderJpaRepository.findById(id);
    }

    @GetMapping(value = "/findByUserId")
    public List<Order> findByUserId(@RequestParam int id) {
        return orderJpaRepository.findByUserId(id);
    }

    @GetMapping(value = "/{id}")
    public List<Order> findByUserIdPath(@PathVariable int id) {
        return orderJpaRepository.findByUserId(id);
    }

    @PostMapping(value = "/load")
    public Optional<Order> loadUser(@RequestBody Order order) {
//        orderService.saveOrder(order);
        orderJpaRepository.save(order);
        return orderJpaRepository.findById(order.getId());
    }
}
