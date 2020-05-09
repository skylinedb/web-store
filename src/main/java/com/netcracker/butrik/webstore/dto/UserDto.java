package com.netcracker.butrik.webstore.dto;

import com.netcracker.butrik.webstore.model.Discount;
import java.util.List;

public class UserDto {


    private int id;

    private String first_name;

    private String last_name;

    private String email;

    private String pass;

    private Discount discount_id;

    private boolean admin_toggle;

    private List<OrderDto> orders;

    private List<ContactDto> contacts;

    public UserDto() {
    }

    public UserDto( String first_name, String last_name, String email, String pass
    ) {
//        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.pass = pass;
//        this.discount_id = discount_id;
//        this.admin_toggle = admin_toggle;
//        this.contacts=contacts;
//        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Discount getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(Discount discount_id) {
        this.discount_id = discount_id;
    }

    public boolean isAdmin_toggle() {
        return admin_toggle;
    }

    public void setAdmin_toggle(boolean admin_toggle) {
        this.admin_toggle = admin_toggle;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
    }
}
