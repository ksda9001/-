package com.commons.entity;

//用户沟通系统
public class Sender {
    private String user;
    private String message;

    public Sender(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public Sender() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
