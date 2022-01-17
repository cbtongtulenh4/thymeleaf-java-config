package com.minhphuc.web.dto;

import com.minhphuc.validation.PasswordMatches;

import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {

    @NotNull
    private String userName;
    @NotNull
    private String email;
    private String password;
    private String matchingPassword;
    private boolean enable;

    public UserDto(){
        this.enable = false;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEnable() {
        return enable;
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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matcherPassword) {
        this.matchingPassword = matcherPassword;
    }

    @Override
    public String toString(){
        return "Email: " + email + "Password: " + password + "MatchingPassword: " + matchingPassword;
    }

}
