package com.accenture.flowerShop.entity.order;

import com.accenture.flowerShop.entity.flower.Flower;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FLOWER_IN_ORDER")
public class FlowerInOrder implements Serializable {
    private static final long serialVersionUID = -667422515623740884L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @ManyToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private long quantity;
    public FlowerInOrder(){}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
