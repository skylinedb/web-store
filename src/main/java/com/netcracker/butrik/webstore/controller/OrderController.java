package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.dto.OrderDto;
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
    public List<OrderDto> findAll() {
        return orderService.findAll();
    }

    @GetMapping(value = "/findAll/user")
    public List<OrderDto> findAll_withUser() {
        return orderService.findAll_withUser();
    }

    @GetMapping(value = "/findById")
    public OrderDto findById(@RequestParam int id) {
        return orderService.findById_withUser(id);
    }

    @GetMapping(value = "/findById/user")
    public OrderDto findById_withUser(@RequestParam int id) {
        return orderService.findById(id);
    }

    @GetMapping(value = "/findByUserId")
    public List<OrderDto> findByUserId(@RequestParam int id) {
        return orderService.findByUserId(id);
    }

    @GetMapping(value = "/findByUserId/user")
    public List<OrderDto> findByUserId_withUser(@RequestParam int id) {
        return orderService.findByUserId_withUser(id);
    }

    @GetMapping(value = "/getOrdersByDateAndUserId")
    public List<OrderDto> getOrdersByDateAndUserId(@RequestParam String startDate, String endDate, int userId) {
        return orderService.getOrdersByDateAndUserId(startDate, endDate, userId);
    }

    @PostMapping(value = "/save")
    public void loadOrder(@RequestBody @Valid OrderDto orderDto) {
        orderService.save(orderDto);
    }

    @PutMapping(value = "/update")
    public void updateOrder(@RequestBody @Valid OrderDto orderDto) {
        orderService.update(orderDto);
    }

    @PostMapping(value = "/delete")
    public void deleteOrder(@RequestBody OrderDto orderDto) {
        orderService.delete(orderDto);
    }

}
