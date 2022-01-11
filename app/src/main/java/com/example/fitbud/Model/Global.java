package com.example.fitbud.Model;

import android.app.Application;

public class Global extends Application {
    private String username;
    private String email;
    private String userType;

    public Global(String username, String email, String userType) {
        this.username = username;
        this.email = email;
        this.userType = userType;
    }

    public Global(){}

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}