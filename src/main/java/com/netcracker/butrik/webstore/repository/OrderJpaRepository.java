package com.netcracker.butrik.webstore.repository;

import com.netcracker.butrik.webstore.model.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

    Order findById(int id);

    List<Order> findByUserId(int userId);

    @Query("SELECT o FROM Order o WHERE (o.timestamp BETWEEN :startDate AND :endDate) AND o.userId=:userId")
    List<Order> getOrdersByDateAndUserId(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
