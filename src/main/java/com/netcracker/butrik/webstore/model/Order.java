package com.netcracker.butrik.webstore.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ADDRESS")
    @Size(min = 5, message = "Адрес должен быть больше 5 символов")
    @Size(max = 100, message = "Адрес не может быть больше 100 символов")
    private String address;

    @Column(name = "USER_ID", updatable = false, insertable = false)
    @Positive
    private int userId;

    @Column(name = "TIMESTAMP")
    @PastOrPresent
    private java.time.LocalDateTime timestamp;

    @Column(name = "SUMM")
    @Positive
    private double summ;

    @Column(name = "SUMM_DISCOUNT")
    @Positive
    private double summ_discount;

    @Column(name = "DISCOUNT_PERCENT")
    @Positive
    @Max(value = 99, message = "Процент не может быть больше 99")
    private int discount_percent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "ORDERS_PRODUCTS",
        joinColumns = @JoinColumn(name = "ORDER_ID"),
        inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private List<Product> products;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
