package com.andreaga.chooseyourchef.entities;

import com.andreaga.utility.entities.Entity;

import java.sql.Date;

public class Utente extends Entity {

    private String username;
    private String password;
    private String nome;
    private String cognome;
    private Date dob;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = Date.valueOf(dob);
    }

    @Override
    public String toString() {
        return super.toString() + "Nome: " + nome + "\n"
                + "Cognome: " + cognome + "\n"
                + "Data di nascita: " + dob + "\n";
    }
}
