package com.netcracker.butrik.webstore.controller;

import com.netcracker.butrik.webstore.dto.ProductDto;
import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.service.ProductService;
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
@RequestMapping("${requestMapping.product}")
@CrossOrigin(origins = "${cors.frontend.url}")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/findAll")
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/findById")
    public ProductDto findById(@RequestParam int id) {
        return productService.findById(id);
    }

    @PostMapping(value = "/save")
    public ProductDto loadProduct(@RequestBody @Valid ProductDto productDto) {
        productService.save(productDto);
        return productService.findById(productDto.getId());
    }

    @PutMapping(value = "/update")
    public ProductDto updateProduct(@RequestBody @Valid ProductDto productDto) {
        productService.save(productDto);
        return productService.findById(productDto.getId());
    }

    @PostMapping(value = "/delete")
    public void deleteProduct(@RequestBody ProductDto productDto) {
        productService.delete(productDto);
    }
}
