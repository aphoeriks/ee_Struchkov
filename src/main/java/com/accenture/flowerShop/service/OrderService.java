package com.accenture.flowerShop.service;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.dao.OrderDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.entity.order.FlowerInOrder;
import com.accenture.flowerShop.entity.order.Order;
import com.accenture.flowerShop.model.CartInfo;
import com.accenture.flowerShop.model.CartLine;
import com.accenture.flowerShop.model.OrderDto;
import com.accenture.flowerShop.model.PaginationResult;
import com.accenture.flowerShop.session.SessionScopeAccountData;
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
import java.math.RoundingMode;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private FlowerDAO flowerDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private SessionScopeAccountData accountData;

    @Transactional
    public void createOrder( CartInfo cart) throws Exception{
        Order order = new Order();
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountDAO.findAccount(currentUserName).get();
        int discount = account.getDiscount();
        account.addOrder(order);

        long totalCost = 0;
        FlowerInOrder flowerInOrder;
        for (CartLine cartLine: cart.getCartLines()) {
            Flower flower = flowerDAO.findFlower(cartLine.getName());
            long flowersInStock = flower.getQuantity();
            long quantityInOrder = cartLine.getQuantity();
            if(flowersInStock<quantityInOrder){

                throw new Exception("На складе не достаточно цветов для заказа");
            }
            flower.setQuantity(
                    flowersInStock-quantityInOrder
            );
            totalCost += (cartLine.getPrice().doubleValue()*100)*quantityInOrder;
            flowerInOrder = orderDAO.createFlowerInOrder(new FlowerInOrder(flower,order,quantityInOrder));
            flower.addFlowerInOrder(flowerInOrder);
            order.addFlowerInOrder(flowerInOrder);
            flowerDAO.updateFlower(flower);
        }
        BigDecimal cost = new BigDecimal(totalCost*(100-discount)/10000.0).setScale(2, RoundingMode.HALF_DOWN);
        order.setPriceTotal(cost);
        order.setStatus(Order.STATUS_CREATE);
        orderDAO.createOrder(order);
        accountDAO.updateAccount(account);
    }
    @Transactional
    public BigDecimal payOrder(long id) throws Exception{
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account currentAccount = accountDAO.findAccount(currentUserName).get();
        Order order = orderDAO.findOrderById(id);
        if(order.getPriceTotal().compareTo(currentAccount.getBalance()) > 0){
            throw new Exception("Не достаточно средств");
        }
        currentAccount.setBalance(
                currentAccount.getBalance().subtract(
                        order.getPriceTotal()));
        order.setStatus(Order.STATUS_PAID);
        accountDAO.updateAccount(currentAccount);
        orderDAO.updateOrder(order);
        accountData.setBalance(currentAccount.getBalance());
        return currentAccount.getBalance();
    }
    @Transactional
    public void closeOrder(long id) throws  Exception{
        Order order = orderDAO.findOrderById(id);
        order.setStatus(Order.STATUS_CLOSED);
        Date currentDate = new Date();
        order.setCloseDate(currentDate);
        orderDAO.updateOrder(order);
    }
    @Transactional
    public PaginationResult<OrderDto> queryOrders(
            int page,
            int maxResult,
            int maxNavigationPage,
            String sortType
    ) {
        return this.queryOrders(page,maxResult,maxNavigationPage,sortType,true);
    }
    @Transactional
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
                orders.get("createDate"),
                orders.get("status"),
                orders.get("id")));
        if(onlyThisAccountOrders){
            String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            query1.where(builder.and(builder.equal(orders.get("account").get("login"), currentUserName)));
        }
        if (sortType.equals("sortByDate") ) {
            query1.orderBy(builder.desc(orders.get("createDate")));
        }else
        if (sortType.equals("sortByStatus")) {
            query1.orderBy(builder.desc(orders.get("status")));
        }

        Query query = session.createQuery(query1);
        return new PaginationResult<>(query, page, maxResult, maxNavigationPage);
    }
}
