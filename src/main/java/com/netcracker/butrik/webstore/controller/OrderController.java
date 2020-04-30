package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.service.OrderService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    private OrderService orderService;

    @GetMapping(value = "/findAll")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping(value = "/findById")
    public Order findById(@RequestParam int id) {
        return orderService.findById(id);
    }

    @GetMapping(value = "/findByUserId")
    public List<Order> findByOrderId(@RequestParam int id) {
        return orderService.findByUserId(id);
    }

    @PostMapping(value = "/save")
    public Order loadOrder(@RequestBody @Valid Order order) {
        orderService.save(order);
        return orderService.findById(order.getId());
    }

    @PutMapping(value = "/update")
    public Order updateOrder(@RequestBody @Valid Order order) {
        orderService.update(order);
        return orderService.findById(order.getId());
    }

    @PostMapping(value = "/delete")
    public void deleteOrder(@RequestBody Order order) {
        orderService.delete(order);
    }
}
