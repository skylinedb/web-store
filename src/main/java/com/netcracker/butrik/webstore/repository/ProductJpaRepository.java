package com.netcracker.butrik.webstore.repository;

import com.netcracker.butrik.webstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
}
