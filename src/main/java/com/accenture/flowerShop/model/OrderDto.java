package com.accenture.flowerShop.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDto {
    private BigDecimal price;
    private Date date;
    private String status;
    private long id;
    public OrderDto( BigDecimal price, Date date, String status, long id){
        this.price = price;
        this.date = date;
        this.status = status;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
