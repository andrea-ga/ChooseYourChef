package com.andreaga.chooseyourchef.entities;

public class Cuoco extends Utente {

    private String citta;
    private int valutazione;
    private double costoOrario;

    public String getCitta() {
        return citta;
    }
    public void setCitta(String citta) {
        this.citta = citta;
    }
    public int getValutazione() {
        return valutazione;
    }
    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }
    public double getCostoOrario() {
        return costoOrario;
    }
    public void setCostoOrario(double costoOrario) {
        this.costoOrario = costoOrario;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Città: " + citta + "\n" +
                "Valutazione: "  + valutazione + "\n" +
                "Costo orario: " + costoOrario + "\n";
    }
}
