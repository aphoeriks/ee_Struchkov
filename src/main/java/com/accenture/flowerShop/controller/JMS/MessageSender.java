package com.accenture.flowerShop.controller.JMS;

import com.accenture.flowerShop.entity.account.Account;
import com.accenture.flowerShop.model.jms.MessageDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Component
public class MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;
    public void sendMessage(final Account account) {
        MessageDiscount messageDiscount = new MessageDiscount(account.getLogin(),0);

        jmsTemplate.send("out_queue", new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(messageDiscount);
                return objectMessage;
            }
        });
    }

}
