package com.accenture.flowerShop.entity.account;

import com.accenture.flowerShop.entity.order.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

    private static final long serialVersionUID = 2410246785624653099L;

    public static final String ROLE_CUSTOMER = "CUSTOMER";
    @Id
    @Column(name = "LOGIN",length = 20)
    private String login;
    @Column(name = "PASSWORD",length = 20,nullable = false)
    private String password;
    @Column(name = "ROLE",length = 20,nullable = false)
    private String Role;
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;
    @Column(name = "BALANCE",nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;
    @Column(name = "DISCOUNT", nullable = false)
    private Integer discount;
    @Column(name = "NAME",length = 20,nullable = false)
    private String name;
    @Column(name = "SURNAME",length = 20,nullable = false)
    private String surname;
    @Column(name = "PATRONYMIC",length = 20,nullable = false)
    private String patronymic;
    @Column(name = "ADDRESS",length = 40,nullable = false)
    private String address;
    @Column(name = "PHONE", nullable = false,length = 13)
    private String phone;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "account")
    private List<Order> orders;

public void setContact(String address,String phone,
                          String name, String surname, String patronymic){
    setAddress(address);
    setPhone(phone);
    setName(name);
    setSurname(surname);
    setPatronymic(patronymic);
}
public void setDefaultCommerce(){
    setBalance(2000);
    setDiscount(5);
}
public void setBalance(double value){
    setBalance(new BigDecimal(value).setScale(2, RoundingMode.HALF_DOWN));
}
    public Account(String login, String password, String role,
                   String address,String phone,
                   String name, String surname, String patronymic){
        setLogin(login);
        setPassword(password);
        setRole(role);
        setActive(true);
        setContact(address,phone,name,surname,patronymic);
        setDefaultCommerce();
    }
    public Account(String login, String password, String role){
        setLogin(login);
        setPassword(password);
        setRole(role);
        setActive(true);
    }

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
        order.setAccount(this);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}