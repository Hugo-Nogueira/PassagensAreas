package com.example.hugo.trabalhoandroid.Model;

public class Origem {
    Integer id;
    String aeroporto, cidade;

    public Origem() {
    }

    public Origem(String aeroporto) {
        this.aeroporto = aeroporto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAeroporto() {
        return aeroporto;
    }

    public void setAeroporto(String aeroporto) {
        this.aeroporto = aeroporto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
