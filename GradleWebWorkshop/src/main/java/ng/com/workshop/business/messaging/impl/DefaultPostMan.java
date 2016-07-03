package ng.com.workshop.business.messaging.impl;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import ng.com.workshop.business.messaging.PostMan;


@Service("postman")
public class DefaultPostMan implements PostMan {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultPostMan.class);

    @Autowired
    JmsOperations jmsOperations;


    @Override
    public void sendMessage(String queue, Serializable message) {
        LOG.info("sending message: {}", message);
        jmsOperations.convertAndSend(queue, message);
    }


    @Override
    public void receiveMessage(String queue, Object message) {
        LOG.info("attempting to receive message");
        Object mail = jmsOperations.receiveAndConvert();
        LOG.info("received message: {}", mail);
    }


    @JmsListener(destination = "workshop.queue")
    public void handleQueue(Message message) {
        try {
            LOG.info("attempting to receive message {}", ((ObjectMessage) message).getObject());
        } catch (JMSException e) {
            e.printStackTrace();
            JmsUtils.convertJmsAccessException(e);
        }
    }
}
