package com.netcracker.butrik.webstore.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

    private int id;

    private String address;

    private int userId;

    private java.time.LocalDateTime timestamp;

    private double summ;

    private double summ_discount;

    private int discount_percent;

    private UserDto user;

    private List<ProductDto> products;

    public OrderDto(int id, String address, int userId, LocalDateTime timestamp, List<ProductDto> products) {
        this.id = id;
        this.address = address;
        this.userId = userId;
        this.timestamp = timestamp;
        this.products = products;
    }

    public OrderDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public double getSumm_discount() {
        return summ_discount;
    }

    public void setSumm_discount(double summ_discount) {
        this.summ_discount = summ_discount;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) {
        this.discount_percent = discount_percent;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
