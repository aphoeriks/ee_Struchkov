package com.accenture.flowerShop.entity.order;

import com.accenture.flowerShop.entity.account.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {
    private static final long serialVersionUID = -7220004040117595057L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "PRICE_TOTAL")
    private BigDecimal priceTotal;
    @ManyToOne
    private Account account;
    @OneToMany(mappedBy = "order")
    private List<FlowerInOrder> flowersInOrder;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderInfo info;

    public Order(){
        flowersInOrder = new ArrayList<FlowerInOrder>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public void addFlowerInOrder(FlowerInOrder flowerInOrder){
        this.flowersInOrder.add(flowerInOrder);
    }

    public List<FlowerInOrder> getFlowersInOrder() {
        return flowersInOrder;
    }

    public void setFlowersInOrder(List<FlowerInOrder> flowersInOrder) {
        this.flowersInOrder = flowersInOrder;
    }

    public OrderInfo getInfo() {
        return info;
    }

    public void setInfo(OrderInfo info) {
        this.info = info;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }
}
