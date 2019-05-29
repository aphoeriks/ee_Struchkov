package com.accenture.flowerShop.controller;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.entity.flower.Flower;
import com.accenture.flowerShop.model.rest.ArrayWrapper;
import com.accenture.flowerShop.model.rest.JsonResponse;
import com.accenture.flowerShop.model.rest.ObjectWrapper;
import com.accenture.flowerShop.service.AccountService;
import com.accenture.flowerShop.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")

public class RestController {
    @Autowired
    AccountService accountService;
    @Autowired
    FlowerService flowerService;
    @GetMapping("/checkLogin/{login}")
    public Boolean loginIsValid(@PathVariable("login") String login) {
        return accountService.loginIsFree(login);
    }

    
    @PostMapping(value = "/flower",produces = "application/json")
    public @ResponseBody JsonResponse addFlower(@RequestBody Flower flower){

        return new JsonResponse("ok", flower.getName());
    }
}
