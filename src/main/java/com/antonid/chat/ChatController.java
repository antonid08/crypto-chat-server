package com.antonid.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public void send(Message message) throws Exception {
/*        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getSender(), message.getText(), time);*/
//nothing
    }
}
