package com.netcracker.butrik.webstore.repository;

import com.netcracker.butrik.webstore.model.Product;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {

    Product findById(int id);

    @Query("SELECT o.products FROM Order o WHERE (o.timestamp BETWEEN :startDate AND :endDate) AND o.userId=:userId")
    List<Product> getProductsByDateAndUserId(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
