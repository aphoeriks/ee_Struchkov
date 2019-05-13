package com.accenture.flowerShop.entity.flower;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STOCK")
public class Stock implements Serializable {
    private static final long serialVersionUID = -6160786723834487314L;
    @Id
    @Column(name = "NAME",length = 20, nullable = false)
    private String name;
    @Column(name = "QUANTITY")
    private long quantity;

    @MapsId
    @OneToOne
    private Flower flower;

    public Stock(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}
