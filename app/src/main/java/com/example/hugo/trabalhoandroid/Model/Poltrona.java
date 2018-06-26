package com.example.hugo.trabalhoandroid.Model;

public class Poltrona {
    Integer assento;
    Boolean ocupado;
    String origem, destino, dataVoo, aviao;
    Double valorPassagem;

    public Poltrona() {
    }

    public Poltrona(Integer assento, Boolean ocupado, String origem, String destino, String dataVoo, String aviao, Double valorPassagem) {
        this.assento = assento;
        this.ocupado = ocupado;
        this.origem = origem;
        this.destino = destino;
        this.dataVoo = dataVoo;
        this.aviao = aviao;
        this.valorPassagem = valorPassagem;
    }

    public Integer getAssento() {
        return assento;
    }

    public void setAssento(Integer assento) {
        this.assento = assento;
    }

    public Boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataVoo() {
        return dataVoo;
    }

    public void setDataVoo(String dataVoo) {
        this.dataVoo = dataVoo;
    }

    public String getAviao() {
        return aviao;
    }

    public void setAviao(String aviao) {
        this.aviao = aviao;
    }

    public Double getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(Double valorPassagem) {
        this.valorPassagem = valorPassagem;
    }
}
