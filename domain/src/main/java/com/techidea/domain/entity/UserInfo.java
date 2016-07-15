package com.techidea.domain.entity;

public class UserInfo {

    private String type;
    private String username;

    public UserInfo() {
    }

    public UserInfo(String type, String username) {
        this.type = type;
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
