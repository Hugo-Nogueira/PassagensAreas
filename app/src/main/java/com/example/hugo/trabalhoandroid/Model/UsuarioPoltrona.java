package com.example.hugo.trabalhoandroid.Model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioPoltrona {
    Integer id;
    String nome, email, token;
    List<Poltrona> passagens;

    public UsuarioPoltrona() {
    }

    public UsuarioPoltrona(Integer id, String nome, String email, String token, List<Poltrona> poltronas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.token = token;
        this.passagens = poltronas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Poltrona> getPoltronas() {
        return passagens;
    }

    public void setPoltronas(List<Poltrona> poltronas) {
        this.passagens = poltronas;
    }
}
