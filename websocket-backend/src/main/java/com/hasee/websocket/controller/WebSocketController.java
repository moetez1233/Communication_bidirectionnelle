package com.hasee.websocket.controller;

import com.hasee.websocket.model.Passport;
import com.hasee.websocket.model.UserToNotify;
import com.hasee.websocket.services.CustomSpringEventPublisher;
import com.hasee.websocket.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CustomSpringEventPublisher customSpringEventPublisher;

    private final Logger log= LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

   // notif tous les utilisateur connecter (on ecoute sur "/message")
    @MessageMapping("/send/message")
    public void sendMessage(String message){
        System.out.println(message);
        this.template.convertAndSend("/message",  message); // message return à "/message" et angular on ecoute à cette path "/message"
    }
    // send msg from page to the same page : as to as
    @MessageMapping("/send/privateMessage")
    @SendToUser("/topic/privateMessage")
    public String  sendPrivateMessage(String message, final Principal principal){
        System.out.println(message);
       notificationService.sendPrivateNotification(principal.getName());
       return message+" sending to specific user "+principal.getName();
    }



    @EventListener
    public void sessionDisconnectionHandler(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        Optional<List<UserToNotify>> userToNotify=notificationService.findUserByToken(event.getUser().getName());
        if(userToNotify.isPresent()){
            notificationService.deleteUserDisconnect(userToNotify.get());
            log.info("user connected '{}' is deleted because is disconnected now",event.getUser().getName());
        }
        System.out.println("sessionId : "+sessionId);
    }
    @EventListener
    public void seesionConnection(SessionConnectedEvent event){
        UserToNotify userToNotify=new UserToNotify();
        userToNotify.setTokenUser(event.getUser().getName());
        notificationService.addUserConnected(userToNotify);
        customSpringEventPublisher.publishCustomEvent("connection starter event ");
        log.info("connxion success ");

    }
}
