package com.netcracker.butrik.webstore.dto;

public class ContactTypeDto {

    private int id;

    private String type;

    public ContactTypeDto(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public ContactTypeDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
