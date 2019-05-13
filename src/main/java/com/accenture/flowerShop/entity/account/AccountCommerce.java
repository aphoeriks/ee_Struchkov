package com.accenture.flowerShop.entity.account;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
@Entity
@Table(name = "ACCOUNT_COMMERCE")
public class AccountCommerce implements Serializable {
    private static final long serialVersionUID = -1070557834035487012L;
    @Id
    @Column(name = "LOGIN",length = 20)
    private String login;
    @Column(name = "BALANCE",nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;
    @Column(name = "DISCOUNT", nullable = false)
    private int discount;

    @OneToOne
    @MapsId
    private Account account;

    public AccountCommerce(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
