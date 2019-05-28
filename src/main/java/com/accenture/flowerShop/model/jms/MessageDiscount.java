package com.accenture.flowerShop.model.jms;

import java.io.Serializable;

public class MessageDiscount implements Serializable {
    String login;
    int discount;
public MessageDiscount(String login, int discount){
    this.login = login;
    this.discount = discount;
}
public MessageDiscount(){login = ""; discount = 0;}
public MessageDiscount(MessageDiscount messageDiscount){
    this.login = messageDiscount.login;
    this.discount = messageDiscount.discount;
}
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
