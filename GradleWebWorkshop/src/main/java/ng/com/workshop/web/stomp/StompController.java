package ng.com.workshop.web.stomp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import ng.com.workshop.model.workshop.WorkTool;


@Controller("stompController")
public class StompController {

    private static final Logger LOG = LoggerFactory.getLogger(StompController.class);


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String handleHolla(String hail) throws Exception {
        String confirmedHail = String.format("confiring message: %s", hail);
        Thread.sleep(3000);
        LOG.info("hailing message : {}", confirmedHail);
        return confirmedHail;
    }


    @SendTo("/topic/greetings")
    public WorkTool handleRegistrationFeedback(WorkTool worktool) throws Exception {
        String confirmedHail = String.format("confirming worktool: %s", worktool);
        LOG.info("confirmedHail: {}", confirmedHail);
        return worktool;
    }


    @SubscribeMapping({ "/hello-sub" })
    public String handleSubscription() throws Exception {
        String hail = "subscribed to hello";
        LOG.info("i subscribed to {}", hail);
        return hail;
    }
}
