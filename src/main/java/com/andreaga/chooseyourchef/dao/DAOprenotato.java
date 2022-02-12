package com.andreaga.chooseyourchef.dao;

import com.andreaga.chooseyourchef.entities.Prenotato;
import com.andreaga.utility.db.Database;
import com.andreaga.utility.db.IDAO;
import com.andreaga.utility.entities.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOprenotato implements IDAO {

    @Autowired
    private Database db;

    @Autowired
    private ApplicationContext ac;

    @Override
    public List<Entity> elenco() {
        List<Entity> ris = new ArrayList<>();

        List<Map<String,String>> prenotati = db.rows("select * from prenotati");

        for (Map<String,String> riga : prenotati) {
            Prenotato p;
            p = ac.getBean(Prenotato.class,riga);

            ris.add(p);
        }

        return ris;
    }

    @Override
    public boolean elimina(int id) {
        String query = "delete from prenotati where id = ?";

        return db.update(query,id+"");
    }

    @Override
    public boolean modifica(Entity e) {
        if(!(e instanceof Prenotato))
            return false;

        Prenotato p = (Prenotato) e;

        String query = "update prenotati set " +
                "idcliente = ?" +
                ",idcuoco = ?" +
                ",prenotazione = ?" +
                ",fasciaoraria = ?" +
                ",npersone = ? " +
                "where id = ?";

        return db.update(query,p.getIdCliente()+"",p.getIdCuoco()+"",p.getPrenotazione()+"",
                p.getFasciaOraria(),p.getNpersone()+"",p.getId()+"");
    }

    @Override
    public boolean salva(Entity e) {
        if(!(e instanceof Prenotato))
            return false;

        Prenotato p = (Prenotato) e;

        String query = "insert into prenotati (idcliente, idcuoco, prenotazione, fasciaoraria, npersone) "
                + "values (?,?,?,?,?)";


        return db.update(query,p.getIdCliente()+"",p.getIdCuoco()+"",p.getPrenotazione()+"",
                p.getFasciaOraria(),p.getNpersone()+"");
    }

    public List<Entity> elencoPerIdCuoco(String idcuoco) {
        List<Entity> ris = new ArrayList<>();

        for(Entity e : elenco())
            if(((Prenotato)e).getIdCuoco() == Integer.parseInt(idcuoco))
                ris.add(e);

        return ris;
    }

    public List<Entity> elencoPerIdCliente(String idcliente) {
        List<Entity> ris = new ArrayList<>();

        for(Entity e : elenco())
            if(((Prenotato)e).getIdCliente() == Integer.parseInt(idcliente))
                ris.add(e);

        return ris;
    }
}
