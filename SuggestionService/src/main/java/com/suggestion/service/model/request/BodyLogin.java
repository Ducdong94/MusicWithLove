package com.suggestion.service.model.request;

public class BodyLogin {
    private String email;
    private String password;

    public BodyLogin() {
    }

    public BodyLogin(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
