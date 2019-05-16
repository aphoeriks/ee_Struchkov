package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.model.FlowerInfo;
import com.accenture.flowerShop.model.PaginationResult;

public interface FlowerDAO {



    Flower findFlower(String name);


    PaginationResult<FlowerInfo> queryFlowers(
            int page,
            int maxResult,
            int maxNavigationPage,
            String likeName,
            double priceMin,
            double priceMax
    );
}
