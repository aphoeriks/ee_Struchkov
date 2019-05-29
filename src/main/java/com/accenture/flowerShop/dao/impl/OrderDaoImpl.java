package com.accenture.flowerShop.dao.impl;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.dao.OrderDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.entity.order.FlowerInOrder;
import com.accenture.flowerShop.entity.order.Order;
import com.accenture.flowerShop.model.OrderDto;
import com.accenture.flowerShop.model.PaginationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;


@Service
public class OrderDaoImpl implements OrderDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private FlowerDAO flowerDAO;
@Transactional
@Override
public FlowerInOrder createFlowerInOrder(FlowerInOrder flowerInOrder){
    sessionFactory.getCurrentSession().persist(flowerInOrder);
    return flowerInOrder;
}

    @Transactional
    @Override
    public Order createOrder( Order order) throws Exception{
        sessionFactory.getCurrentSession().persist(order);
        return order;
    }
    @Override
    public Order findOrderById(Long id){
        Session session =sessionFactory.getCurrentSession();
        return session.find(Order.class, id);
    }


    @Override
    @Transactional
    public Order updateOrder(Order order){
        sessionFactory.getCurrentSession().update(order);
        return order;
    }

}
