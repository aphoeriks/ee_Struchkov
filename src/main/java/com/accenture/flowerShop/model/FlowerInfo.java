package com.accenture.flowerShop.model;

import java.math.BigDecimal;

public class FlowerInfo {
    private String name;
    private BigDecimal price;
    private long inStock;
    private long amountToBuy;
    public FlowerInfo(){}
    public FlowerInfo(
               String name,
               BigDecimal price,
               long inStock){

        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    public long getInStock() {
        return inStock;
    }

    public void setInStock(long inStock) {
        this.inStock = inStock;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
