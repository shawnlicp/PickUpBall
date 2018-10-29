package com.example.shawnli.pickupball.Model;

/**
 * Created by shawnli on 10/2/2018.
 */

public class User {
    private String username;
    private String password;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
