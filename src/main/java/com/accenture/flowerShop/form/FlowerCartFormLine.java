package com.accenture.flowerShop.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class FlowerCartFormLine {
    @Size(max = 30)
    private String name;
    @DecimalMin(value = "0.01")
    private BigDecimal price;
    @Min(1)
    private long quantity;
    public FlowerCartFormLine(){}
    public FlowerCartFormLine(String name, BigDecimal price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
