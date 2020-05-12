package com.netcracker.butrik.webstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CONTACT_TYPE")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TYPE")
    @NotBlank(message = "Необходимо заполнить поле")
    @Size(min = 2, message = "Поле не может быть меньше 2-х символов")
    @Size(max = 30, message = "Поле не может быть больше 30 символов")
    private String type;

//    @OneToOne(mappedBy = "contactType")
//    private Contact contact;

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
