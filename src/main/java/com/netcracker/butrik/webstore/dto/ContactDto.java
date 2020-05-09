package com.netcracker.butrik.webstore.dto;

public class ContactDto {

    private int id;

//    private ContactTypeDto contactType;

    private String type_label;

    private String value;

    private int userId;

    public ContactDto(String type_label, String value, int userId) {
        this.type_label = type_label;
        this.value = value;
        this.userId = userId;
    }

    public ContactDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public ContactTypeDto getContactType() {
//        return contactType;
//    }
//
//    public void setContactType(ContactTypeDto contactType) {
//        this.contactType = contactType;
//    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType_label() {
        return type_label;
    }

    public void setType_label(String type_label) {
        this.type_label = type_label;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
