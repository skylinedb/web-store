package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.repository.ProductJpaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    @Autowired
    ProductJpaRepository productJpaRepository;

    private static Logger log = LoggerFactory.getLogger(ProductService.class);

    public void save(final Product product) {
        productJpaRepository.save(product);
        log.info("Product: ID:"
            +product.getId()
            +" "+product.getProduct_name()
            +" "+product.getProduct_price()
            +"  SAVE OPERATION");
    }

    public void update(final Product product) {
        productJpaRepository.save(product);
        log.info("Product: ID:"
            +product.getId()
            +" "+product.getProduct_name()
            +" "+product.getProduct_price()
            +"  UPDATE OPERATION");
    }

    public void delete(final Product product) {
        productJpaRepository.delete(product);
        log.info("Product: ID:"
            +product.getId()
            +" "+product.getProduct_name()
            +" "+product.getProduct_price()
            +"  DELETE OPERATION");
    }

    public Product findById(final int id) {
        log.info("Product: ID:" + id + "  FindByID OPERATION");
        return productJpaRepository.findById(id);
    }


    public List<Product> findAll() {
        log.info("Product: FindAll OPERATION");
        return productJpaRepository.findAll();
    }
}
