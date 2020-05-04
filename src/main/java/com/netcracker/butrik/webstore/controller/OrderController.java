package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.dto.OrderDto;
import com.netcracker.butrik.webstore.dto.mapper.impl.OrderMapperImpl;
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
    @Autowired
    private OrderMapperImpl orderMapper;

    @GetMapping(value = "/findAll")
    public List<OrderDto> findAll() {
        List<Order> orderList = orderService.findAll();
        return orderMapper.toDtos(orderList);
    }

    @GetMapping(value = "/findAll/user")
    public List<OrderDto> findAllwithUser() {
        List<Order> orderList = orderService.findAll();
        return orderMapper.toDtosWithUser(orderList);
    }

    @GetMapping(value = "/findById")
    public OrderDto findById(@RequestParam int id) {
        Order order = orderService.findById(id);
        return orderMapper.toDto(order);
    }

    @GetMapping(value = "/findByUserId")
    public List<OrderDto> findByOrderId(@RequestParam int id) {
        List<Order> orderList = orderService.findByUserId(id);
        return orderMapper.toDtos(orderList);
    }

    @GetMapping(value = "/findByUserId/user")
    public List<OrderDto> findByOrderIdWithUser(@RequestParam int id) {
        List<Order> orderList = orderService.findByUserId(id);
        return orderMapper.toDtosWithUser(orderList);
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

    @GetMapping(value = "/test")
    public double getSummUserId(@RequestParam int userId) {
        return orderService.testForDiscountByUserId(userId);
    }
}
