package com.andreaga.chooseyourchef.dao;

import com.andreaga.chooseyourchef.entities.Cuoco;
import com.andreaga.utility.db.Database;
import com.andreaga.utility.db.IDAO;
import com.andreaga.utility.entities.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOcuoco implements IDAO {

    @Autowired
    private Database db;

    @Autowired
    private ApplicationContext ac;

    @Override
    public List<Entity> elenco() {
        List<Entity> ris = new ArrayList<>();

        List<Map<String,String>> cuochi = db.rows("select * from utenti inner join cuochi on cuochi.id = utenti.id");

        for (Map<String,String> riga : cuochi) {
            Cuoco c;
            c = ac.getBean(Cuoco.class,riga);

            ris.add(c);
        }

        return ris;
    }

    @Override
    public boolean elimina(int id) {
        String query = "delete from utenti where id = ?";

        return db.update(query,id+"");
    }

    @Override
    public boolean modifica(Entity e) {
        if (!(e instanceof Cuoco))
            return false;

        Cuoco c = (Cuoco) e;

        String query = "update utenti set username=?, password=md5(?), nome = ?, cognome = ?, dob = ? where id = ?";
        String query2 = "update cuochi set citta = ?, valutazione=?, costoorario=? where id = ?";


        if (db.update(query,c.getUsername(),c.getPassword(),c.getNome(),c.getCognome(),c.getDob()+"",c.getId()+""))
            if(db.update(query2,c.getCitta(),c.getValutazione()+"",c.getCostoOrario()+"",c.getId()+""))
                return true;

        return false;
    }

    @Override
    public boolean salva(Entity e) {
        if (!(e instanceof Cuoco))
            return false;

        Cuoco c = (Cuoco) e;

        String query = "insert into utenti (username,password,nome,cognome,dob) values (?,md5(?),?,?,?)";
        String query2 = "insert into cuochi (id,citta,valutazione,costoorario) values ((select max(id) from utenti),?,?,?)";

        if (db.update(query,c.getUsername(),c.getPassword(),c.getNome(),c.getCognome(),c.getDob()+""))
            if(db.update(query2,c.getCitta(),c.getValutazione()+"",c.getCostoOrario()+""))
                return true;

        return false;
    }

    public boolean login(String username, String password) {
        String query = "select * from utenti inner join cuochi on cuochi.id = utenti.id "
                + "where username = ? and password = md5(?)";

        return db.row(query,username,password) != null;
    }

    public String trovaId(String username) {
        String query = "select utenti.id from utenti inner join cuochi on utenti.id = cuochi.id " +
                "where username = ?";

        Map<String,String> id = db.row(query,username);

        return id.get("id");
    }

    public List<Entity> trovaDisponibilita(String prenotazione, String fasciaOraria, String citta) {
        List<Entity> ris = new ArrayList<>();

        String query = "select utenti.*, cuochi.* from utenti inner join cuochi on utenti.id = cuochi.id "
                + "where citta = ? and utenti.id not in "
                + "(select utenti.id from utenti inner join prenotati on utenti.id = prenotati.idcuoco "
                + "where prenotati.prenotazione = ? and prenotati.fasciaoraria = ?)";

        List<Map<String,String>> cuochi = db.rows(query,citta,prenotazione,fasciaOraria);

        for (Map<String,String> riga : cuochi) {
            Cuoco c;
            c = ac.getBean(Cuoco.class,riga);

            ris.add(c);
        }

        return ris;
    }

    public Entity trovaCuoco(String id) {
        Map<String,String> riga = db.row("select * from utenti inner join cuochi on cuochi.id = utenti.id "
                + "where utenti.id = ?",id);

        Cuoco c;
        c = ac.getBean(Cuoco.class,riga);

        return c;
    }
}
