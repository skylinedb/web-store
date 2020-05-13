package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.dto.DiscountDto;
import com.netcracker.butrik.webstore.dto.DiscountDto;
import com.netcracker.butrik.webstore.dto.OrderDto;
import com.netcracker.butrik.webstore.service.DiscountService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${requestMapping.discount}")
@CrossOrigin(origins = "${cors.frontend.url}")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping(value = "/findAll")
    public List<DiscountDto> findAll() {
        return discountService.findAll();
    }

    @PostMapping(value = "/save")
    public void loadDiscount(@RequestBody @Valid DiscountDto discountDto) {
        discountService.save(discountDto);
    }

    @PutMapping(value = "/update")
    public void updateDiscount(@RequestBody @Valid DiscountDto discountDto) {
        discountService.update(discountDto);
    }

    @PostMapping(value = "/delete")
    public void deleteDiscount(@RequestBody DiscountDto discountDto) {
        discountService.delete(discountDto);
    }
}
