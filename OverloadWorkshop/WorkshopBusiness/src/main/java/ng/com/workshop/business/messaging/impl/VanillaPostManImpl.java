package ng.com.workshop.business.messaging.impl;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ng.com.workshop.business.messaging.PostMan;


@Service("vanillaPostMan")
public class VanillaPostManImpl implements PostMan {

    private static final Logger LOG = LoggerFactory.getLogger(VanillaPostManImpl.class);


    @Override
    public void sendMessage(String queue, Serializable message) {
        LOG.info("sending message pack: {}", message);
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection conn = null;
        Session session = null;
        try {
            conn = cf.createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = new ActiveMQQueue(queue);
            ObjectMessage mail = session.createObjectMessage();
            mail.setObject(message);
            session.createProducer(destination).send(mail);
        } catch (JMSException e) {
            LOG.error("problem occurred while sending Message. Message: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (JMSException e) {
                LOG.error("cannot close connections. Message: {}", e.getMessage());
                e.printStackTrace();
            }
        }
    }


    @Override
    public void receiveMessage(String queue, Object message) {
        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection conn = null;
        Session session = null;
        try {
            conn = cf.createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = new ActiveMQQueue(queue);
            Message mail = session.createConsumer(destination).receive();
            Object mailPackage = ((ObjectMessage) mail).getObject();
            LOG.info("You got mail: {}", mailPackage);
            conn.start();
        } catch (JMSException e) {
            LOG.error("problem occurred while sending Message. Message: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (JMSException e) {
                LOG.error("cannot close connections. Message: {}", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
