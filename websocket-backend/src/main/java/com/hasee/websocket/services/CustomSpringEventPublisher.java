package com.hasee.websocket.services;

import com.hasee.websocket.model.CustomSpringEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    public void publishCustomEvent(final String message){
        System.out.println("publish custom event ");
        CustomSpringEvent customSpringEvent=new CustomSpringEvent(this,message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
