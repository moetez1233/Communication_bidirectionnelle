package com.hasee.websocket.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
public class UserToNotify implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tokenUser;
    private String status="Valid";
    private String name;

}
