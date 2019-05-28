package com.accenture.flowerShop.controller.JMS;

import com.accenture.flowerShop.dao.AccountDAO;
import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.entity.account.AccountCommerce;
import com.accenture.flowerShop.model.jms.MessageDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Component
public class MessageUpdater {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    JmsTemplate jmsTemplate;

    private static final String OUT_QUEUE = "out_queue";
    private static final String IN_QUEUE = "in_queue";

    @JmsListener(destination = OUT_QUEUE)
    public void updateMessage(final Message<MessageDiscount> message) throws JMSException {

        MessageDiscount messageDiscount = message.getPayload();
        messageDiscount.setDiscount(messageDiscount.getDiscount());
        jmsTemplate.send(IN_QUEUE,new MessageCreator(){
            @Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(messageDiscount);
                return objectMessage;
            }
        });
    }
}
