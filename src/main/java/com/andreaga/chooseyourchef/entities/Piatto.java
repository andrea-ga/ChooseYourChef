package com.andreaga.chooseyourchef.entities;

import com.andreaga.utility.entities.Entity;

public class Piatto extends Entity {

    private int idcuoco;
    private String nome;
    private String cucina;
    private String tipo;
    private String allergeni;

    public int getIdcuoco() {
        return idcuoco;
    }

    public void setIdcuoco(int idcuoco) {
        this.idcuoco = idcuoco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCucina() {
        return cucina;
    }

    public void setCucina(String cucina) {
        this.cucina = cucina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAllergeni() {
        return allergeni;
    }

    public void setAllergeni(String allergeni) {
        this.allergeni = allergeni;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Id Cuoco: " + idcuoco + "\n" +
                "Nome: " + nome + "\n" +
                "Cucina: " + cucina + "\n" +
                "Tipo: " + tipo + "\n" +
                "Allergeni: " + allergeni + "\n";
    }
}
