package com.andreaga.chooseyourchef.dao;

import com.andreaga.chooseyourchef.entities.Piatto;
import com.andreaga.utility.db.Database;
import com.andreaga.utility.db.IDAO;
import com.andreaga.utility.entities.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOpiatto implements IDAO {

    @Autowired
    private Database db;

    @Autowired
    private ApplicationContext ac;

    @Override
    public List<Entity> elenco() {
        List<Entity> ris = new ArrayList<>();

        List<Map<String,String>> piatti = db.rows("select * from piatti");

        for (Map<String,String> riga: piatti)
        {
            Piatto p;
            p = ac.getBean(Piatto.class,riga);

            ris.add(p);
        }

        return ris;
    }

    @Override
    public boolean elimina(int id) {

        String query="delete from piatti where id=?";
        return db.update(query, id + ""); // c'e' il cascade, non eliminare in preparati idpiatto-idcuoco.

    }

    @Override
    public boolean modifica(Entity e) {
        Piatto p=(Piatto) e ;
        String query="update piatti set idcuoco=?,nome=?,cucina=?,tipo=?,allergeni=?" + " where id=?";
        return db.update(query,p.getIdcuoco()+"",p.getNome(),p.getCucina(),p.getTipo(),p.getAllergeni(),p.getId()+"");

    }

    @Override
    public boolean salva(Entity e) {
        Piatto p = (Piatto) e;
        String query="insert into piatti (idcuoco,nome,cucina,tipo,allergeni) values (?,?,?,?,?)";
        return db.update(query,p.getIdcuoco()+"",p.getNome(),p.getCucina(),p.getTipo(),p.getAllergeni());
    }

    public List<Entity> elencoPerId(String idcuoco) {
        List<Entity> ris = new ArrayList<>();

        for(Entity e : elenco())
            if(((Piatto)e).getIdcuoco() == Integer.parseInt(idcuoco))
                ris.add(e);

        return ris;
    }
}
