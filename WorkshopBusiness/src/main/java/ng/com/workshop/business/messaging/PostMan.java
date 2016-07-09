package ng.com.workshop.business.messaging;

import java.io.Serializable;


public interface PostMan {

    public void sendMessage(String queue, Serializable message);


    public void receiveMessage(String queue, Object message);
}
