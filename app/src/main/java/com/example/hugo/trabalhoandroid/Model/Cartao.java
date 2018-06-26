package com.example.hugo.trabalhoandroid.Model;

public class Cartao {
    Integer tarja;
    Double valor;
    String bandeira, validade, status;
    Long numero;

    public Cartao() {
    }

    public Long getNumero() {

        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getTarja() {
        return tarja;
    }

    public void setTarja(Integer tarja) {
        this.tarja = tarja;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cartao(Long numero, Integer tarja, Double valor, String bandeira, String validade, String status) {

        this.numero = numero;
        this.tarja = tarja;
        this.valor = valor;
        this.bandeira = bandeira;
        this.validade = validade;
        this.status = status;
    }
}
