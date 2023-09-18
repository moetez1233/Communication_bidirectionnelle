package com.hasee.websocket.servicesImpl;

import com.hasee.websocket.model.UserToNotify;

import java.util.List;
import java.util.Optional;

public interface UserToNotifyImplement  {
    List<UserToNotify> getAllUerToNotify();
    void addUserConnected(UserToNotify userToNotify);
    void deleteUserDisconnect(List<UserToNotify> userToNotify);
    Optional<List<UserToNotify>> findUserByToken(String token);

}
