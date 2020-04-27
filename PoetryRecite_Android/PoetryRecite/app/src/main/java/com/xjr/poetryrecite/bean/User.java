package com.xjr.poetryrecite.bean;

/**
 * Created by Raffello on 2019/4/26.
 */

public class User {
    private Long userid;
    private String username;
    private String password;
    private String email;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String toString(){
        return "userid:="+userid+"username:="+username+"password:="+password+"email：="+email;
    }
}
