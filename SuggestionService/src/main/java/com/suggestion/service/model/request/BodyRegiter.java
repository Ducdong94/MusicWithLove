package com.suggestion.service.model.request;

public class BodyRegiter {
    private String email;
    private String password;
    private String fullname;
    //Role Admin = 1, Role: User = 2
    private int role;

    public BodyRegiter() {
    }

    public BodyRegiter(String email, String password, String fullname, int role) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "BodyRegiter{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", role=" + role +
                '}';
    }
}
