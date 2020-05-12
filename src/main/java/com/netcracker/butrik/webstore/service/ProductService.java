package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.dto.ProductDto;
import com.netcracker.butrik.webstore.dto.mapper.impl.ProductMapperImpl;
import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.repository.ProductJpaRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    private static Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    ProductJpaRepository productJpaRepository;
    @Autowired
    ProductMapperImpl productMapper;

    public void save(final ProductDto productDto) {
        Product product = productMapper.fromDto(productDto);
        productJpaRepository.save(product);
        log.info("Product: ID:"
            + product.getId()
            + " " + product.getProduct_name()
            + " " + product.getProduct_price()
            + "  SAVE OPERATION");
    }

    public void update(final ProductDto productDto) {
        Product product = productMapper.fromDto(productDto);
        productJpaRepository.save(product);
        log.info("Product: ID:"
            + product.getId()
            + " " + product.getProduct_name()
            + " " + product.getProduct_price()
            + "  UPDATE OPERATION");
    }

    public void delete(final ProductDto productDto) {
        Product product = productMapper.fromDto(productDto);
        productJpaRepository.delete(product);
        log.info("Product: ID:"
            + product.getId()
            + " " + product.getProduct_name()
            + " " + product.getProduct_price()
            + "  DELETE OPERATION");
    }

    public ProductDto findById(final int id) {
        log.info("Product: ID:" + id + "  FindByID OPERATION");
        Product product = productJpaRepository.findById(id);
        return productMapper.toDto(product);
    }


    public List<ProductDto> findAll() {
        log.info("Product: FindAll OPERATION");
        List<Product> products = productJpaRepository.findAll();
        return productMapper.toDtos(products);
    }

    public List<ProductDto> getProductsByDateAndUserId(String startDate, String endDate, int userId) {
        startDate = startDate + " 00:00";
        endDate = endDate + " 23:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
        List<Product> products = productJpaRepository.getProductsByDateAndUserId(startDateTime, endDateTime, userId);
        log.info("Product: getProductsByDateAndUserAndId OPERATION");
        return productMapper.toDtos(products);
    }
}
