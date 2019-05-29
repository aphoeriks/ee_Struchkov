package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.order.FlowerInOrder;
import com.accenture.flowerShop.entity.order.Order;
import com.accenture.flowerShop.model.OrderDto;
import com.accenture.flowerShop.model.PaginationResult;

import javax.transaction.Transactional;
import java.math.BigDecimal;

public interface OrderDAO {



    @Transactional
    FlowerInOrder createFlowerInOrder(FlowerInOrder flowerInOrder);

    @Transactional
    Order createOrder(Order order) throws Exception;

    Order findOrderById(Long id);


    @Transactional
    Order updateOrder(Order order);
}
