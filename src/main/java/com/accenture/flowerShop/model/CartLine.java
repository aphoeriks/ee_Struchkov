package com.accenture.flowerShop.model;

import java.math.BigDecimal;

public class CartLine {
    private String name;
    private BigDecimal price;
    private long quantity;
    public CartLine(
            String name,
            BigDecimal price,
            long quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public CartLine(CartLine cartLine){
        this.name = cartLine.name;
        this.price = cartLine.price;
        this.quantity = cartLine.quantity;
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getTotalPrice(){
        return this.price.multiply(new BigDecimal(this.quantity));
    }
}
