package com.andreaga.chooseyourchef.entities;

import com.andreaga.utility.entities.Entity;

import java.sql.Date;

public class Prenotato extends Entity {

    private int idcliente;
    private int idcuoco;
    private Date prenotazione;
    private String fasciaOraria;
    private int nPersone;

    public int getIdCliente() {
        return idcliente;
    }

    public void setIdCliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdCuoco() {
        return idcuoco;
    }

    public void setIdCuoco(int idcuoco) {
        this.idcuoco = idcuoco;
    }

    public Date getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(String prenotazione) {
        this.prenotazione = Date.valueOf(prenotazione);
    }

    public String getFasciaOraria() {
        return fasciaOraria;
    }

    public void setFasciaOraria(String fasciaOraria) {
        this.fasciaOraria = fasciaOraria;
    }

    public int getNpersone() {
        return nPersone;
    }

    public void setNpersone(int npersone) {
        this.nPersone = npersone;
    }

    @Override
    public String toString() {
        return super.toString() + "Id Cliente: " + idcliente + "\n" +
                "Id Cuoco: " + idcuoco + "\n" +
                "Prenotazione: " + prenotazione + "\n" +
                "Fascia Oraria: " + fasciaOraria + "\n" +
                "Numero Persone: " + nPersone + "\n";
    }
}
