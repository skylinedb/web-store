package com.netcracker.butrik.webstore.dto;

public class ContactDto {

    private int id;

//    private UserDto user;

    private ContactTypeDto contactType;

    private String value;

    public ContactDto(int id, ContactTypeDto contactType, String value) {
        this.id = id;
//        this.user = user;
        this.contactType = contactType;
        this.value = value;
    }

    public ContactDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public UserDto getUser() {
//        return user;
//    }
//
//    public void setUser(UserDto user) {
//        this.user = user;
//    }

    public ContactTypeDto getContactType() {
        return contactType;
    }

    public void setContactType(ContactTypeDto contactType) {
        this.contactType = contactType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
