package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.model.Discount;
import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.DiscountJpaRepository;
import com.netcracker.butrik.webstore.repository.OrderJpaRepository;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import java.util.Arrays;
import java.util.Collections;
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
    @Autowired
    private DiscountJpaRepository discountJpaRepository;

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
        testForDiscountByUserId(user.getId());
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

    public double testForDiscountByUserId(final int userId) {
        List<Order> orderList = orderJpaRepository.findByUserId(userId);
        double order_summ = 0;
        for (int i = 0; i < orderList.size(); i++) {
            order_summ = order_summ + orderList.get(i).getSumm_discount();
        }
        User user = userJpaRepository.findById(userId);
        if (order_summ >= user.getDiscount_id().getSumm()) {
            List<Discount> discountList=discountJpaRepository.findAll();
            discountList.sort(Discount::compareTo);
            for (int i = 0; i < discountList.size(); i++) {
                if (discountList.get(i).getSumm() > order_summ ) {
                    log.info(user.getDiscount_id().getBadge());
                    user.setDiscount_id(discountList.get(i-1));
                    userJpaRepository.save(user);
                    log.info("New Discount:"+user.getDiscount_id().getBadge() + " for User:"
                        + user.getLast_name() + " " + user.getFirst_name());
                    return order_summ;
                }
            }
        }
        return order_summ;
    }
}
