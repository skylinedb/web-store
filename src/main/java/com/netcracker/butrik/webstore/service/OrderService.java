package com.netcracker.butrik.webstore.service;

import com.netcracker.butrik.webstore.dto.OrderDto;
import com.netcracker.butrik.webstore.dto.mapper.impl.OrderMapperImpl;
import com.netcracker.butrik.webstore.dto.mapper.impl.UserMapperImpl;
import com.netcracker.butrik.webstore.model.Discount;
import com.netcracker.butrik.webstore.model.Order;
import com.netcracker.butrik.webstore.model.Product;
import com.netcracker.butrik.webstore.model.User;
import com.netcracker.butrik.webstore.repository.DiscountJpaRepository;
import com.netcracker.butrik.webstore.repository.OrderJpaRepository;
import com.netcracker.butrik.webstore.repository.UserJpaRepository;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DiscountJpaRepository discountJpaRepository;
    @Autowired
    private OrderMapperImpl orderMapper;
    @Autowired
    private UserMapperImpl userMapper;

    public void save(final OrderDto orderDto) {
        Order order = orderMapper.fromDto(orderDto);
        User user = userMapper.fromDto(userService.findById(order.getUserId()));
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
        order.setUser(user);
        orderJpaRepository.save(order);
        double allSumm = testForDiscountByUserId(user.getId());
        log.info("Order: ID:"
            + order.getId()
            + " " + order.getUser().getLast_name()
            + " " + order.getUser().getFirst_name()
            + " " + order.getAddress()
            + " " + allSumm
            + "  SAVE OPERATION");
    }

    public void update(final OrderDto orderDto) {
        Order order = orderMapper.fromDto(orderDto);
        orderJpaRepository.save(order);
        log.info("Order: ID:"
            + order.getId()
            + " " + order.getUser().getLast_name()
            + " " + order.getUser().getFirst_name()
            + " " + order.getAddress()
            + "  UPDATE OPERATION");
    }

    public void delete(final OrderDto orderDto) {
        Order order = orderMapper.fromDto(orderDto);
        orderJpaRepository.delete(order);
        log.info("Order: ID:"
            + order.getId()
            + " " + order.getUser().getLast_name()
            + " " + order.getUser().getFirst_name()
            + " " + order.getAddress()
            + "  DELETE OPERATION");
    }

    public OrderDto findById(final int id) {
        log.info("Order: ID:" + id + "  FindByID OPERATION");
        Order order = orderJpaRepository.findById(id);
        return orderMapper.toDto(order);
    }

    public OrderDto findById_withUser(final int id) {
        log.info("Order: ID:" + id + "  FindByID OPERATION");
        Order order = orderJpaRepository.findById(id);
        return orderMapper.toDtoWithUser(order);
    }

    public List<OrderDto> findAll() {
        log.info("Order: FindAll OPERATION");
        List<Order> orders = orderJpaRepository.findAll();
        return orderMapper.toDtos(orders);
    }

    public List<OrderDto> findAll_withUser() {
        log.info("Order: FindAll_withUser OPERATION");
        List<Order> orders = orderJpaRepository.findAll();
        return orderMapper.toDtosWithUser(orders);
    }

    public List<OrderDto> findByUserId_withUser(final int userId) {
        log.info("Order: UserID:" + userId + "  FindByUserID OPERATION");
        List<Order> orders = orderJpaRepository.findByUserId(userId);
        return orderMapper.toDtosWithUser(orders);
    }

    public List<OrderDto> findByUserId(final int userId) {
        log.info("Order: UserID:" + userId + "  FindByUserID OPERATION");
        List<Order> orders = orderJpaRepository.findByUserId(userId);
        return orderMapper.toDtos(orders);
    }


    public double testForDiscountByUserId(final int userId) {
        List<Order> orderList = orderMapper.fromDtos(findByUserId(userId));
        double order_summ = 0;
        for (int i = 0; i < orderList.size(); i++) {
            order_summ = order_summ + orderList.get(i).getSumm_discount();
        }
        User user = userMapper.fromDto(userService.findById(userId));
        if (order_summ >= user.getDiscount_id().getSumm()) {
            List<Discount> discountList = discountJpaRepository.findAll();
            discountList.sort(Discount::compareTo);
            for (int i = 0; i < discountList.size(); i++) {
                if (order_summ >= discountList.get(i).getSumm()) {
                    log.info(user.getDiscount_id().getBadge());
                    user.setDiscount_id(discountList.get(i));
                }
            }
            userJpaRepository.save(user);
        }
        return order_summ;
    }

//    @Query("SELECT o FROM Order o WHERE o.userId=:userid")
//    public List<Order> getQuery(int userid) {}
//    public List<Product> getProductsByDate(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate) {
//        int userid = 101;
//        List<Product> products;
//        return products;
//    }

    public List<OrderDto> getOrdersByDateAndUserId(String startDate,String endDate, int userId) {
//        String now = "2016-11-09 10:30";
        startDate = startDate + " 00:00";
        endDate = endDate + " 23:59";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);

//        System.out.println("Before : " + now);
//
//        System.out.println("After : " + formatDateTime);
//
//        System.out.println("After : " + formatDateTime.format(formatter));

        log.info("Order: QUERY OPERATION");
        List<Order> orders = orderJpaRepository.getOrdersByDateAndUserId(startDateTime, endDateTime, userId);
        return orderMapper.toDtos(orders);
    }
}
