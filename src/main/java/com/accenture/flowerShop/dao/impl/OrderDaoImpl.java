package com.accenture.flowerShop.dao.impl;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.dao.OrderDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.entity.account.AccountCommerce;
import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.entity.order.FlowerInOrder;
import com.accenture.flowerShop.entity.order.Order;
import com.accenture.flowerShop.entity.order.OrderInfo;
import com.accenture.flowerShop.model.CartInfo;
import com.accenture.flowerShop.model.CartLine;
import com.accenture.flowerShop.model.OrderDto;
import com.accenture.flowerShop.model.PaginationResult;
import javafx.scene.control.Pagination;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    public void save( CartInfo cart) throws Exception{
        Order order = new Order();
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountDAO.findAccount(currentUserName).get();
        int discount = account.getCommerce().getDiscount();
        account.addOrder(order);

        long totalCost = 0;
        FlowerInOrder flowerInOrder;
        for (CartLine cartLine: cart.getCartLines()) {
            Flower flower = flowerDAO.findFlower(cartLine.getName());
            long flowersInStock = flower.getStock().getQuantity();
            long quantityInOrder = cartLine.getQuantity();
            if(flowersInStock<quantityInOrder){

                throw new Exception("На складе не достаточно цветов для заказа");
            }
            flower.getStock().setQuantity(
                    flowersInStock-quantityInOrder
            );
            totalCost += (cartLine.getPrice().doubleValue()*100)*quantityInOrder;
            flowerInOrder = new FlowerInOrder(flower,order,quantityInOrder);
            this.sessionFactory.getCurrentSession().persist(flowerInOrder);
            flower.addFlowerInOrder(flowerInOrder);
            order.addFlowerInOrder(flowerInOrder);
            this.sessionFactory.getCurrentSession().update(flower);
        }
        BigDecimal cost = new BigDecimal(totalCost*(100-discount)/10000.0).setScale(2, RoundingMode.HALF_DOWN);
        order.setPriceTotal(cost);
        OrderInfo info = new OrderInfo();
        info.setStatus(OrderInfo.STATUS_CREATE);
        info.setOrder(order);
        order.setInfo(info);
        this.sessionFactory.getCurrentSession().persist(order);
        this.sessionFactory.getCurrentSession().update(account);
    }
    @Override
    public Order findOrderById(Long id){
        Session session =sessionFactory.getCurrentSession();
        return session.find(Order.class, id);
    }
    @Override
    public OrderInfo findOrderInfoById(Long id){
        Session session =sessionFactory.getCurrentSession();
        return session.find(OrderInfo.class, id);
    }
    @Override
    public PaginationResult<OrderDto> queryOrders(
            int page,
            int maxResult,
            int maxNavigationPage,
            String sortType
    ) {
        return this.queryOrders(page,maxResult,maxNavigationPage,sortType,true);
    }
    @Override
    public PaginationResult<OrderDto> queryOrders(
            int page,
            int maxResult,
            int maxNavigationPage,
            String sortType,
            boolean onlyThisAccountOrders
    ){
        Session session =sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderDto> query1 = builder.createQuery(OrderDto.class);
        Root<Order> orders = query1.from(Order.class);
        query1.select(builder.construct(OrderDto.class, orders.get("priceTotal"),
                                                        orders.get("info").get("createDate"),
                                                        orders.get("info").get("status"),
                                                        orders.get("id")));
        if(onlyThisAccountOrders){
            String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            query1.where(builder.and(builder.equal(orders.get("account").get("login"), currentUserName)));
        }
        if (sortType.equals("sortByDate") ) {
            query1.orderBy(builder.desc(orders.get("info").get("createDate")));
        }else
        if (sortType.equals("sortByStatus")) {
            query1.orderBy(builder.desc(orders.get("info").get("status")));
        }

        Query query = session.createQuery(query1);
        return new PaginationResult<>(query, page, maxResult, maxNavigationPage);
    }
    @Transactional
    @Override
    public BigDecimal payOrder(long id) throws Exception{
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account currentAccount = accountDAO.findAccount(currentUserName).get();
        AccountCommerce currentAccountCommerce = currentAccount.getCommerce();
        Order order = findOrderById(id);
        if(order.getPriceTotal().compareTo(currentAccountCommerce.getBalance()) > 0){
            throw new Exception("Не достаточно средств");
        }
        currentAccountCommerce.setBalance(
                currentAccountCommerce.getBalance().subtract(
                        order.getPriceTotal()));
        OrderInfo orderInfo = order.getInfo();
        orderInfo.setStatus(OrderInfo.STATUS_PAID);
        this.sessionFactory.getCurrentSession().update(orderInfo);
        this.sessionFactory.getCurrentSession().update(currentAccountCommerce);
        return currentAccountCommerce.getBalance();
    }
    @Override
    public void closeOrder(long id) throws  Exception{
        OrderInfo orderInfo = findOrderInfoById(id);
        orderInfo.setStatus(OrderInfo.STATUS_CLOSED);
        Date currentDate = new Date();
        orderInfo.setCloseDate(currentDate);
    }
}
