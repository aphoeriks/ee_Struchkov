package com.accenture.flowerShop.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface FlowerStockWebService {
    @WebResult(name = "IsFlowersStockChanged")
    boolean increaseFlowersStockSize
            (@WebParam(name = "stockIncrease")int count);
    @WebMethod
    String testService();
}
