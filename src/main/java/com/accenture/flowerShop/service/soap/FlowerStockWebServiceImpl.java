package com.accenture.flowerShop.service.soap;

import com.accenture.flowerShop.dao.FlowerDAO;
import com.accenture.flowerShop.entity.flower.Stock;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.accenture.flowerShop.service.soap.FlowerStockWebService",
portName = "8080")
public class FlowerStockWebServiceImpl implements FlowerStockWebService {
    @Autowired
    private FlowerDAO flowerDAO;
    @Override
    public boolean increaseFlowersStockSize(int count){
        List<Stock> stocks = flowerDAO.getAllStocks();
        for(Stock stock:stocks){
            stock.setQuantity(stock.getQuantity()+count);
        }
        try {
            flowerDAO.updateStocks(stocks);
        }catch (Exception e){return false;}
        return true;
    }
    @Override
    public String testService(){
        return "Hello";
    }

}
