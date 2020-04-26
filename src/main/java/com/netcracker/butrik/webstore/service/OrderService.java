package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.OrderJpaRepository;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    public void saveOrder(final Order order) {
        User user = userJpaRepository.findById(order.getUserId());
        double summ = 0, summ_discount;
        int discount = user.getDiscount_id().getPercent();
        List<Product> productList = order.getProducts();
        for (int i = 0; i < productList.size(); i++) {
            summ = summ + productList.get(i).getProduct_price();
        }
        if (discount == 0) {
            summ_discount = summ;
        } else {
            summ_discount = summ - summ * discount / 100;
        }
        order.setSumm(summ);
        order.setSumm_discount(summ_discount);
        order.setDiscount_percent(discount);
        orderJpaRepository.save(order);
    }
}
