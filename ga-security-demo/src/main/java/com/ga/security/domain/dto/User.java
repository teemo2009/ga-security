package com.ga.security.domain.dto;


import com.fasterxml.jackson.annotation.JsonView;

public class User {

    public interface SimpleView {
    }

    public interface DetailView extends SimpleView {
    }

    @JsonView(SimpleView.class)
    private String username;
    @JsonView(DetailView.class)
    private String password;

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
