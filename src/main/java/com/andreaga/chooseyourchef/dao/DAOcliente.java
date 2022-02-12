package com.andreaga.chooseyourchef.dao;

import com.andreaga.chooseyourchef.entities.Cliente;
import com.andreaga.utility.db.Database;
import com.andreaga.utility.db.IDAO;
import com.andreaga.utility.entities.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAOcliente implements IDAO {

    @Autowired
    private Database db;

    @Autowired
    private ApplicationContext ac;

    @Override
    public List<Entity> elenco() {
        List<Entity> ris = new ArrayList<>();

        List<Map<String,String>> clienti = db.rows("select * from utenti inner join clienti on clienti.id = utenti.id");

        for (Map<String,String> riga : clienti) {
            Cliente c;
            c = ac.getBean(Cliente.class,riga);

            ris.add(c);
        }

        return ris;
    }

    @Override
    public boolean elimina(int id) {
        String query="delete from utenti where id=?";

        return db.update(query,id+"");
    }

    @Override
    public boolean modifica(Entity e) {
        Cliente c = (Cliente) e;

        String query = "update utenti set password=md5(?),username=?,nome=?,cognome=?,dob=?" +
                " where id = ?";
        String query2 = "update clienti set budget=? where id=?";

        return db.update(query, c.getPassword(),c.getUsername(),c.getNome(),c.getCognome(),c.getDob()+"",c.getId() + "")
                && db.update(query2, c.getBudget() + "",c.getId() + "");
    }

    @Override
    public boolean salva(Entity e) {
        if(!(e instanceof Cliente))
            return false;

        Cliente c = (Cliente) e;
        String query="insert into utenti(password,username,nome,cognome,dob)" + " values (md5(?),?,?,?,?)";
        String query2="insert into clienti(id,budget)" + " values((select max(id) from utenti),?)";
        if(db.update(query,c.getPassword(),c.getUsername(),c.getNome(),c.getCognome(),c.getDob()+""))
            return db.update(query2,c.getBudget()+"");
        else
            return false;
    }

    public boolean login(String username, String password) {
        String query = "select * from utenti inner join clienti on clienti.id = utenti.id "
                + "where username = ? and password = md5(?)";

        return db.row(query,username,password) != null;
    }

    public String trovaId(String username) {
        String query = "select utenti.id from utenti inner join clienti on utenti.id = clienti.id " +
                "where username = ?";

        Map<String,String> id = db.row(query,username);

        return id.get("id");
    }

    public Entity trovaCliente(String id) {
        Map<String,String> riga = db.row("select * from utenti inner join clienti on clienti.id = utenti.id "
                + "where utenti.id = ?",id);

        Cliente c;
        c = ac.getBean(Cliente.class,riga);

        return c;
    }
}
