package com.netcracker.butrik.webstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DISCOUNT")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "DISCOUNT_BADGE")
    @Size(min = 2, message = "Название должно быть больше 2 символов")
    @Size(max = 10, message = "Название не может быть больше 10 символов")
    private String discount_badge;

    @Column(name = "DISCOUNT_PERCENT")
    @Min(value = 0, message = "Процент не может быть меньше 0")
    @Max(value = 99, message = "Процент не может быть больше 99")
    private int discount_percent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscount_badge() {
        return discount_badge;
    }

    public void setDiscount_badge(String discount_badge) {
        this.discount_badge = discount_badge;
    }

    public int getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(int discount_percent) {
        this.discount_percent = discount_percent;
    }
}
