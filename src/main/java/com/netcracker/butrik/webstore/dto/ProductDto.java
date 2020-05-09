package com.netcracker.butrik.webstore.dto;

public class ProductDto {

    private int id;

    private String product_name;

    private double product_price;

    public ProductDto(int id, String product_name, double product_price) {
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
    }

    public ProductDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
