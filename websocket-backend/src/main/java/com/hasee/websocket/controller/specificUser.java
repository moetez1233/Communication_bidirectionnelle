package com.hasee.websocket.controller;

import com.hasee.websocket.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
public class specificUser {
    private final SimpMessagingTemplate template;

    @Autowired
    private NotificationService notificationService;

    public specificUser(SimpMessagingTemplate template) {
        this.template = template;
    }

    // send message to specific user connected
    @Scheduled(fixedRate = 5000)
    public void greeting() {
        System.out.println("scheduled");
        notificationService.sendGlobalNotification();
        this.template.convertAndSend("/message", "send msg to all users");

    }


    @PostMapping("/send/specificMessageUser/{id}/{message}")
    public void sendMsgTospecificUserById(@PathVariable String id, @PathVariable String message) {
        notificationService.sendPrivateNotification(id);
        this.template.convertAndSendToUser(id, "/specifUser", message);
    }


}
