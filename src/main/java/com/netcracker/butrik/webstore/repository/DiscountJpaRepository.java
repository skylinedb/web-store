package com.netcracker.butrik.webstore.repository;

import com.netcracker.butrik.webstore.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiscountJpaRepository extends JpaRepository<Discount, Integer> {
    Discount findById(int id);

    Discount findByBadge(String badge);

    Discount findByPercent(int percent);
}
