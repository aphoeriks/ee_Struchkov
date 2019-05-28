package com.accenture.flowerShop.controller.JMS;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.entity.account.AccountCommerce;
import com.accenture.flowerShop.model.jms.MessageDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;



import javax.jms.JMSException;

@Component
public class MessageReceiver {
    @Autowired
    AccountDAO accountDAO;

    private static final String IN_QUEUE = "in_queue";
    @JmsListener(destination = IN_QUEUE)
    public void receiveMessage(final Message<MessageDiscount> message) throws JMSException {

        MessageDiscount messageDiscount = message.getPayload();
        try {
            accountDAO.updateDiscount(messageDiscount.getLogin(),messageDiscount.getDiscount());
        }catch (Exception e){
            System.out.println("Couldn`t update");
        }
    }
}

