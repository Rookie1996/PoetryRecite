package com.xjr.model;

public class Admin {
    private long adminid;

    private String adminame;

    private String password;

    private String email;

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public String getAdminame() {
        return adminame;
    }

    public void setAdminame(String adminame) {
        this.adminame = adminame == null ? null : adminame.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}