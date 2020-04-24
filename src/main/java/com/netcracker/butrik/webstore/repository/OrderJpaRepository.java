package com.netcracker.butrik.webstore.repository;

import com.netcracker.butrik.webstore.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int userId);
}
