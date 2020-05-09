package com.netcracker.butrik.webstore.dto;

public class DiscountDto {

    private int id;
    private String badge;
    private int percent;
    private int summ;

    public DiscountDto() {
    }

    public DiscountDto(int id, String badge, int percent, int summ) {
        this.id = id;
        this.badge = badge;
        this.percent = percent;
        this.summ = summ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }
}
