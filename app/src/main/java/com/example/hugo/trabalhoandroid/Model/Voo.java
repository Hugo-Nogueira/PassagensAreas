package com.example.hugo.trabalhoandroid.Model;

public class Voo {
    String dataVoo;
    Integer id, valorPassagem;
    Origem origem;
    Destino destino;
    Aviao aviao;

    public Voo() {
    }

    public Voo(String dataVoo, Integer id, Integer valorPassagem, Origem origem, Destino destino, Aviao aviao) {
        this.dataVoo = dataVoo;
        this.id = id;
        this.valorPassagem = valorPassagem;
        this.origem = origem;
        this.destino = destino;
        this.aviao = aviao;
    }

    public String getDataVoo() {
        return dataVoo;
    }

    public void setDataVoo(String dataVoo) {
        this.dataVoo = dataVoo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(Integer valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }
}
