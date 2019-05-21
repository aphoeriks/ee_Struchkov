package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.entity.flower.Stock;
import com.accenture.flowerShop.model.FlowerInfo;
import com.accenture.flowerShop.model.PaginationResult;

import java.util.List;

public interface FlowerDAO {



    Flower findFlower(String name);


    List<Stock> getAllStocks();

    boolean updateStocks(List<Stock> stocks) throws Exception;

    PaginationResult<FlowerInfo> queryFlowers(
            int page,
            int maxResult,
            int maxNavigationPage,
            String likeName,
            double priceMin,
            double priceMax
    );
}
