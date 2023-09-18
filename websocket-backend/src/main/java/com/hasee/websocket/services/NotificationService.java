package com.hasee.websocket.services;

import com.hasee.websocket.model.ResponceMessage;
import com.hasee.websocket.model.UserToNotify;
import com.hasee.websocket.repository.UserToNotifyRepos;
import com.hasee.websocket.servicesImpl.UserToNotifyImplement;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements UserToNotifyImplement {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private UserToNotifyRepos userToNotifyRepos;

    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate, UserToNotifyRepos userToNotifyRepos){
        this.simpMessagingTemplate=simpMessagingTemplate;
        this.userToNotifyRepos=userToNotifyRepos;   }

    public void sendGlobalNotification(){
        ResponceMessage message=new ResponceMessage("global Notif");
        simpMessagingTemplate.convertAndSend("/Notification/globale",message.getMessage());
    }
    public void sendPrivateNotification(final String userId){
        ResponceMessage message=new ResponceMessage("private Notif");
        simpMessagingTemplate.convertAndSendToUser(userId,"/Notification/private",message.getMessage());
    }

    @Override
    public List<UserToNotify> getAllUerToNotify() {
        return userToNotifyRepos.findAll();
    }

    @Override
    public void addUserConnected(UserToNotify userToNotify) {
        userToNotifyRepos.save(userToNotify);

    }

    @Override
    public void deleteUserDisconnect(List<UserToNotify> userToNotify) {
        userToNotifyRepos.deleteAll(userToNotify);
    }

    @Override
    public Optional<List<UserToNotify>> findUserByToken(String token) {
        return userToNotifyRepos.findUserToNityByToken(token);
    }
}
