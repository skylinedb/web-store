package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.ProductJpaRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @GetMapping(value = "/findAll")
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    @GetMapping(value = "/findById")
    public Optional<Product> findById(@RequestParam int id) {
        return productJpaRepository.findById(id);
    }

    @PostMapping(value = "/load")
    public Optional<Product> loadUser(@RequestBody@Valid Product product) {
        productJpaRepository.save(product);
        return productJpaRepository.findById(product.getId());
    }
}
