package com.netcracker.butrik.webstore.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRST_NAME")
    @NotBlank(message = "Необходимо указать Имя")
    private String first_name;

    @Column(name = "LAST_NAME")
    @NotBlank(message = "Необходимо указать Фамилию")
    private String last_name;

    @Column(name = "EMAIL")
    @Email(message = "Email должен быть корректным адресом электронной почты")
    private String email;

    @Column(name = "PASS")
    @NotBlank(message = "Необходимо указать пароль")
    private String pass;

    @Column(name = "ADMIN_TOGGLE")
//    @NotBlank
    private boolean admin_toggle;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @OneToOne
    @JoinColumn(name = "DISCOUNT_ID")
    private Discount discount_id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

//    public User(int id, @NotBlank(message = "Необходимо указать Имя") String first_name
//        , @NotBlank(message = "Необходимо указать Фамилию") String last_name
//        , @Email(message = "Email должен быть корректным адресом электронной почты") String email
//        , @NotBlank(message = "Необходимо указать пароль") String pass
//        , Discount discount_id) {
//        this.id = id;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.email = email;
//        this.pass = pass;
//        this.admin_toogle = false;
//        this.discount_id = discount_id;
//    }

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

    public void setLast_name(String last_name) { this.last_name = last_name; }

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

    public void setAdmin_toggle(boolean admin_toogle) {
        this.admin_toggle = admin_toogle;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    //    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }
}
