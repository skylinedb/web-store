package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.repository.OrderJpaRepository;
import com.netcracker.butrik.webstore.service.OrderService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${requestMapping.order}")
@CrossOrigin(origins = "${cors.frontend.url}")
public class OrderController {

    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/findAll")
    public List<Order> findAll() {
        return orderJpaRepository.findAll();
    }

    @GetMapping(value = "/findById")
    public Order findById(@RequestParam int id) {
        return orderJpaRepository.findById(id);
    }

    @GetMapping(value = "/findByUserId")
    public List<Order> findByOrderId(@RequestParam int id) {
        return orderJpaRepository.findByUserId(id);
    }

    @PostMapping(value = "/load")
    public Order loadOrder(@RequestBody @Valid Order order) {
        orderService.saveOrder(order);
//        orderJpaRepository.save(order);
        return orderJpaRepository.findById(order.getId());
    }

    @PutMapping(value = "/update")
    public Order updateOrder(@RequestBody @Valid Order order) {
        orderJpaRepository.save(order);
        return orderJpaRepository.findById(order.getId());
    }

    @PostMapping(value = "/delete")
    public void deleteOrder(@RequestBody Order order) {
        orderJpaRepository.delete(order);
    }
}
