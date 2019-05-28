package com.accenture.flowerShop.config;

import com.accenture.flowerShop.service.soap.FlowerStockWebServiceImpl;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


@Configuration
    public class ServiceConfiguration {
        @Bean
        public SpringBus cxf() {
            return new SpringBus();
        }
        @Bean
        public Endpoint endpoint() {
            EndpointImpl endpoint = new EndpointImpl(cxf(), new FlowerStockWebServiceImpl());
            endpoint.publish("/flowerStockWebService");
            return endpoint;
        }
    }

