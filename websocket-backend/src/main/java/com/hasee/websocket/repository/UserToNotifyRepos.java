package com.hasee.websocket.repository;

import com.hasee.websocket.model.UserToNotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserToNotifyRepos extends JpaRepository<UserToNotify,Long> {

    @Query(value = "select * from user_to_notify wp where wp.token_user =:token",nativeQuery = true)
    Optional<List<UserToNotify>> findUserToNityByToken(@Param("token") String token);
}
