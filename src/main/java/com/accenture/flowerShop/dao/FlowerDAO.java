package com.accenture.flowerShop.dao;

import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.model.FlowerInfo;
import com.accenture.flowerShop.model.PaginationResult;

import java.util.List;

public interface FlowerDAO {



    Flower findFlower(String name);


    Flower updateFlower(Flower flower);

    List<Flower> getAllFlowers();




}
