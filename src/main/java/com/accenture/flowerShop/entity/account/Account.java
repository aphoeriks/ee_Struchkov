package com.accenture.flowerShop.entity.account;

import com.accenture.flowerShop.entity.order.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

    private static final long serialVersionUID = 2410246785624653099L;

    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String ROLE_ADMIN = "ADMIN";
    @Id
    @Column(name = "LOGIN",length = 20)
    private String login;
    @Column(name = "PASSWORD",length = 20,nullable = false)
    private String password;
    @Column(name = "ROLE",length = 20,nullable = false)
    private String Role;
    @Column(name = "ACTIVE", nullable = false)
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "account")
    private List<Order> orders;
    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    private AccountContact contact;

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    private AccountCommerce commerce;

    public Account( ){
        this.orders = new ArrayList<Order>();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void addOrder(Order order){
        this.orders.add(order);
    }

    public AccountContact getContact() {
        return contact;
    }

    public void setContact(AccountContact contact) {
        this.contact = contact;
    }

    public AccountCommerce getCommerce() {
        return commerce;
    }

    public void setCommerce(AccountCommerce commerce) {
        this.commerce = commerce;
    }
}