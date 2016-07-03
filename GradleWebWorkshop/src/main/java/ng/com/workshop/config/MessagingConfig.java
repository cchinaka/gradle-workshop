package ng.com.workshop.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.DynamicDestinationResolver;


@Configuration
@EnableJms
@PropertySource("classpath:app.properties")
public class MessagingConfig {

    @Value("${jms.broker.url:tcp://localhost:61616}")
    private String connectionBrokerUrl;

    @Value("${jms.concurrency:3-10}")
    private String concurrency;


    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory conn = new ActiveMQConnectionFactory();
        conn.setBrokerURL(connectionBrokerUrl);
        return conn;
    }


    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jms = new JmsTemplate(connectionFactory);
        jms.setDefaultDestinationName("workshop.queue");
        return jms;
    }


    @Bean
    public DestinationResolver destinationResolver() {
        return new DynamicDestinationResolver();
    }


    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, DestinationResolver destinationResolver) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestinationResolver(destinationResolver);
        factory.setConcurrency(concurrency);
        return factory;
    }
}
