package com.example.hugo.trabalhoandroid.Model;

public class Usuario {
    String token, email, login, senha, nome;

    public Usuario() {
    }

    public Usuario(String token, String email, String login, String senha, String nome) {
        this.token = token;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
