package com.antonid.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/message/to/{username}")
    public void message(@Payload Message message, @DestinationVariable("username") String username, Principal principal) {

        message.setSender(principal.getName());

        simpMessagingTemplate.convertAndSendToUser(username, "/message", message);
    }
}
