package com.accenture.flowerShop.entity.order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ORDER_INFO")
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 3679087640051504362L;
    public static final String STATUS_CREATE = "Создан";
    public static final String STATUS_PAID = "Оплачен";
    public static final String STATUS_CLOSED = "Выполнен";
    @Id
    @Column(name = "ID")
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLOSE_DATE")
    private Date closeDate;
    @Column(name = "STATUS")
    private String status;
    @OneToOne
    @MapsId
    private Order order;

    public OrderInfo(){}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @PrePersist
    public void prePersist() {
        createDate = new Date();
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
