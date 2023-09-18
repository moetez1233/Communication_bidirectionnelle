package com.hasee.websocket.model;

public class ResponceMessage {
    private String message;

    public ResponceMessage() {
    }

    public ResponceMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
