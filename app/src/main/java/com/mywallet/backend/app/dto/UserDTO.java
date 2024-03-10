package com.mywallet.backend.app.dto;

public class UserDTO {
    private String username;
    private String token;

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
