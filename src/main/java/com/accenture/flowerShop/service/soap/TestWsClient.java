package com.accenture.flowerShop.service.soap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class TestWsClient {
    public static void testSOAPFromClient() {
        String soapServiceUrl = "http://localhost:8080/service/flowerStock";

        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(FlowerStockWebService.class);
        factoryBean.setAddress(soapServiceUrl);

        FlowerStockWebService flowerStockWebService = (FlowerStockWebService) factoryBean.create();
        String test = flowerStockWebService.testService();
        System.out.println("Result: " + test);
    }
}
