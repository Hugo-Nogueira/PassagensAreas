package com.example.hugo.trabalhoandroid.Model;

import java.io.Serializable;

public class Aviao implements Serializable {
    Integer id, capacidade;
    String prefixo, modelo;

    public Aviao() {
    }

    public Aviao(Integer id, Integer capacidade, String modelo, String prefixo) {
        this.id = id;
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.prefixo = prefixo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }
}
