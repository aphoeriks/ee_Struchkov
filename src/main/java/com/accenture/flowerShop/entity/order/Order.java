package com.accenture.flowerShop.entity.order;

import com.accenture.flowerShop.entity.account.Account;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {
    public static final String STATUS_CREATE = "Создан";
    public static final String STATUS_PAID = "Оплачен";
    public static final String STATUS_CLOSED = "Выполнен";
    private static final long serialVersionUID = -7220004040117595057L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "PRICE_TOTAL")
    private BigDecimal priceTotal;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLOSE_DATE")
    private Date closeDate;
    @Column(name = "STATUS")
    private String status;
    @ManyToOne
    private Account account;
    @OneToMany(mappedBy = "order")
    private List<FlowerInOrder> flowersInOrder;

    @PrePersist
    protected void onCreate() {
        setCreateDate(new Date());
    }



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
        flowerInOrder.setOrder(this);
        this.flowersInOrder.add(flowerInOrder);
    }

    public List<FlowerInOrder> getFlowersInOrder() {
        return flowersInOrder;
    }

    public void setFlowersInOrder(List<FlowerInOrder> flowersInOrder) {
        this.flowersInOrder = flowersInOrder;
    }



    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
