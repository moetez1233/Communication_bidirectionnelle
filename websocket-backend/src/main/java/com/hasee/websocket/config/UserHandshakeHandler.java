package com.hasee.websocket.config;

import com.hasee.websocket.model.UserToNotify;
import com.hasee.websocket.services.NotificationService;
import com.sun.security.auth.UserPrincipal;
import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final Logger logger= LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Autowired
    private NotificationService notificationService;

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        //return super.determineUser(request, wsHandler, attributes);
        final String randomId = UUID.randomUUID().toString();
        logger.info("request : "+request);
        logger.info("wsHandler : "+wsHandler);
        logger.info("attributes : "+attributes);
        logger.info("token : ",request.getURI());
        logger.info("User with ID '{}' opened the page", randomId+"AX-STD");

        return new UserPrincipal(request.getURI().toString().split("token=")[1]);
    }
}
