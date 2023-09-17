package com.ecommerce.chatservice.controllers;

import com.ecommerce.chatservice.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @MessageMapping("/register")
    @SendTo("/topic/public")
    public ChatMessage register(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ){
        headerAccessor.getSessionAttributes().put("userId", chatMessage.getSenderId());
        return chatMessage;
    }

    @MessageMapping("/send")
    @SendTo("/topic/public")
    public ChatMessage send(
            @Payload ChatMessage chatMessage
    ){
        return chatMessage;
    }
}
