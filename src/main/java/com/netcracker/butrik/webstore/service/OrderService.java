package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.OrderJpaRepository;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    private static Logger log = LoggerFactory.getLogger(OrderService.class);

    public void save(final Order order) {
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
        log.info("Order: ID:"
            +order.getId()
            +" "+order.getUser().getLast_name()
            +" "+order.getUser().getFirst_name()
            +" "+order.getAddress()
            +"  SAVE OPERATION");
    }

    public void update(final Order order) {
        orderJpaRepository.save(order);
        log.info("Order: ID:"
            +order.getId()
            +" "+order.getUser().getLast_name()
            +" "+order.getUser().getFirst_name()
            +" "+order.getAddress()
            +"  UPDATE OPERATION");
    }

    public void delete(final Order order) {
        orderJpaRepository.delete(order);
        log.info("Order: ID:"
            +order.getId()
            +" "+order.getUser().getLast_name()
            +" "+order.getUser().getFirst_name()
            +" "+order.getAddress()
            +"  DELETE OPERATION");
    }

    public Order findById(final int id) {
        log.info("Order: ID:"+id+"  FindByID OPERATION");
        return orderJpaRepository.findById(id);
    }

    public List<Order> findAll() {
        log.info("Order: FindAll OPERATION");
        return orderJpaRepository.findAll();
    }

    public List<Order> findByUserId(final int userId) {
        log.info("Order: UserID:"+userId+"  FindByUserID OPERATION");
        return orderJpaRepository.findByUserId(userId);
    }
}
