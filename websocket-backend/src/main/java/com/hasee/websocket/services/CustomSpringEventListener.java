package com.hasee.websocket.services;

import com.hasee.websocket.model.CustomSpringEvent;
import com.hasee.websocket.model.UserToNotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private NotificationService notificationService;

    public CustomSpringEventListener(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void onApplicationEvent(CustomSpringEvent customSpringEvent) {
        System.out.println("i'm the listener of the even :"+customSpringEvent.getMessage() );

    }
}
