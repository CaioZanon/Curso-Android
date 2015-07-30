package com.example.administrador.teste.model.entities;

import com.example.administrador.teste.model.persistence.SQLiteLoginRepository;

import java.util.List;

/**
 * Created by CaioZanon on 30/07/2015.
 */
public class Login {
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (id != null ? !id.equals(login.id) : login.id != null) return false;
        if (username != null ? !username.equals(login.username) : login.username != null)
            return false;
        return !(password != null ? !password.equals(login.password) : login.password != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public static Login getUsers() {
        return SQLiteLoginRepository.getInstance().getUsers();
    }


}
