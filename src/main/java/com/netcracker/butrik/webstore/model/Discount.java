package com.netcracker.butrik.webstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "DISCOUNT")
public class Discount implements Comparable<Discount>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "BADGE")
//    @Size(min = 2, message = "Название должно быть больше 2 символов")
//    @Size(max = 10, message = "Название не может быть больше 10 символов")
    private String badge;

    @Column(name = "PERCENT")
//    @Min(value = 0, message = "Процент не может быть меньше 0")
//    @Max(value = 99, message = "Процент не может быть больше 99")
    private int percent;

    @Column(name = "SUMM")
//    @Min(value = 0, message = "Процент не может быть меньше 0")
//    @Max(value = 99, message = "Процент не может быть больше 99")
    private int summ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String discount_badge) {
        this.badge = discount_badge;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int discount_percent) {
        this.percent = discount_percent;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    @Override
    public int compareTo(Discount compareDiscount) {
        int comparePercent = ((Discount) compareDiscount).getPercent();

        return this.percent - comparePercent;
    }
}
