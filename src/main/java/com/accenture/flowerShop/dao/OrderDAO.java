package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.order.Order;
import com.accenture.flowerShop.entity.order.OrderInfo;
import com.accenture.flowerShop.model.CartInfo;
import com.accenture.flowerShop.model.OrderDto;
import com.accenture.flowerShop.model.PaginationResult;

import java.math.BigDecimal;

public interface OrderDAO {


    void save( CartInfo cart) throws Exception;

    Order findOrderById(Long id);

    OrderInfo findOrderInfoById(Long id);


    PaginationResult<OrderDto> queryOrders(
            int page,
            int maxResult,
            int maxNavigationPage,
            String sortType
    );

    PaginationResult<OrderDto> queryOrders(
            int page,
            int maxResult,
            int maxNavigationPage,
            String sortType,
            boolean onlyThisAccountOrders
    );

    BigDecimal payOrder(long id) throws Exception;

    void closeOrder(long id) throws  Exception;
}
