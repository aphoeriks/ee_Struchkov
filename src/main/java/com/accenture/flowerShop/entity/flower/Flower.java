package com.accenture.flowerShop.entity.flower;


import com.accenture.flowerShop.entity.order.FlowerInOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "FLOWERS")
public class Flower implements Serializable {
    private static final long serialVersionUID = -2156548555669962336L;
    @Id
    @Column(name = "NAME",length = 20, nullable = false)
    private String name;
    @Column(name = "PRICE",nullable = false,precision = 10,scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "flower")
    private List<FlowerInOrder> flowersInOrder;
    @OneToOne(mappedBy = "flower")
    private Stock stock;

    public Flower(){}


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

    public List<FlowerInOrder> getFlowersInOrder() {
        return flowersInOrder;
    }

    public void setFlowersInOrder(List<FlowerInOrder> flowersInOrder) {
        this.flowersInOrder = flowersInOrder;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
