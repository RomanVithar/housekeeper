package com.key_manager.dto;

import java.util.Objects;

public class OneWrite {
    private String password;
    private String login;
    private String from;
    private String description;

    public OneWrite() {
    }

    public OneWrite(String password, String login, String from, String description) {
        this.password = password;
        this.login = login;
        this.from = from;
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneWrite oneWrite = (OneWrite) o;
        return Objects.equals(password, oneWrite.password) &&
                Objects.equals(login, oneWrite.login) &&
                Objects.equals(from, oneWrite.from) &&
                Objects.equals(description, oneWrite.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, login, from, description);
    }

    @Override
    public String toString() {
        return "пароль:   " + password + '\n' +
                "логин:    " + login + '\n' +
                "от:       " + from + '\n' +
                "описание: " + description + '\n';
    }
}
