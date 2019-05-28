package com.accenture.flowerShop.controller;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.model.rest.ArrayWrapper;
import com.accenture.flowerShop.model.rest.JsonResponse;
import com.accenture.flowerShop.model.rest.ObjectWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")

public class RestController {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    FlowerDAO flowerDAO;
    @GetMapping("/checkLogin/{login}")
    public Boolean loginIsValid(@PathVariable("login") String login) {
        return !accountDAO.findAccount(login).isPresent();
    }

    @GetMapping(value = "/flowers",produces = "application/json")
    public @ResponseBody ArrayWrapper<Flower> listFlowersHandler(){
        List<Flower> flowers = flowerDAO.getAllFlowers();
        return new ArrayWrapper<>(flowers);
    }
    @GetMapping(value = "/flowers/{name}",produces = "application/json")
    public @ResponseBody ObjectWrapper<Flower> getFlower(@PathVariable("name") String name){
        Flower flower = flowerDAO.findFlower(name);
        return new ObjectWrapper<>(flower);
    }
    @PostMapping(value = "/flower",produces = "application/json")
    public @ResponseBody JsonResponse addFlower(@RequestBody Flower flower){

        return new JsonResponse("ok", flower.getName());
    }
}
