package com.accenture.flowerShop.entity.flower;


import com.accenture.flowerShop.entity.order.FlowerInOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FLOWERS")
public class Flower implements Serializable {
    private static final long serialVersionUID = -2156548555669962336L;
    @Id
    @Column(name = "NAME",length = 30, nullable = false)
    private String name;
    @Column(name = "PRICE",nullable = false,precision = 10,scale = 2)
    private BigDecimal price;
    @Column(name = "QUANTITY")
    private long quantity;

    @OneToMany(mappedBy = "flower")
    private List<FlowerInOrder> flowersInOrder;
    public Flower(){
        flowersInOrder = new ArrayList<FlowerInOrder>();
    }
    public Flower(String name){
        this(name, new BigDecimal(4), 300);
    }
    public Flower(String name, BigDecimal price){
        this(name,price,300);
    }
    public Flower(String name, BigDecimal price, long quantity){
        this.name = name;
        this.price = price;
        flowersInOrder = new ArrayList<FlowerInOrder>();
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
    public void addFlowerInOrder(FlowerInOrder flowerInOrder){
        flowerInOrder.setFlower(this);
        this.flowersInOrder.add(flowerInOrder);

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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flower)) return false;
        Flower flower = (Flower) o;
        return name.equals(flower.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    @Override
    public String toString(){return this.name;}

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
