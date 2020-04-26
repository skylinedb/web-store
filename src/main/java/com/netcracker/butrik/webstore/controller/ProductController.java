package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.repository.ProductJpaRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${requestMapping.product}")
@CrossOrigin(origins = "${cors.frontend.url}")
public class ProductController {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @GetMapping(value = "/findAll")
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }

    @GetMapping(value = "/findById")
    public Product findById(@RequestParam int id) {
        return productJpaRepository.findById(id);
    }

    @PostMapping(value = "/load")
    public Product loadProduct(@RequestBody@Valid Product product) {
        productJpaRepository.save(product);
        return productJpaRepository.findById(product.getId());
    }

    @PutMapping(value = "/update")
    public Product updateProduct(@RequestBody @Valid Product product) {
        productJpaRepository.save(product);
        return productJpaRepository.findById(product.getId());
    }

    @PostMapping(value = "/delete")
    public void deleteProduct(@RequestBody Product product) {
        productJpaRepository.delete(product);
    }
}
