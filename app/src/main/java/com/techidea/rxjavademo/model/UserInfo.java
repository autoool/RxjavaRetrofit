package com.techidea.rxjavademo.model;

import javax.inject.Inject;

/**
 */
public class UserInfo {
    private String type;
    private String username;

    @Inject
    UserInfo() {
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
