package com.andreaga.utility.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//SINGLETON
public class Database {

    private static Database instance = null;
    private Connection c;
    private Database() {}

    public static Database getInstance() {
        if(instance == null)
            instance = new Database();

        return instance;
    }

    public void connetti(String nomeDb, String user, String pass) {
        if(c!=null)
            return;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+nomeDb,
                    user,
                    pass
            );
        }
        catch(Exception e)
        {
            System.out.println("Problemi connessione al DB:");
            e.printStackTrace();
        }
    }

    public List<Map<String,String>> rows(String query){
        List<Map<String,String>> ris = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(query);
            ResultSet rs = p.executeQuery();

            while(rs.next()) {
                Map<String,String> riga = new LinkedHashMap<>();

                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    riga.put(
                            rs.getMetaData().getColumnLabel(i),
                            rs.getString(i)
                    );
                }

                ris.add(riga);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return ris;
    }

    public Map<String,String> row(String query){
        try {
            return rows(query).get(0);
        } catch(Exception e) {
            return null;
        }
    }

    public boolean update(String query) {
        boolean ris = true;

        try {
            PreparedStatement p = c.prepareStatement(query);
            p.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            ris = false;
        }

        return ris;
    }

    public List<Map<String,String>> rows(String query, String... params){
        List<Map<String,String>> ris = new ArrayList<>();

        try {
            PreparedStatement p = c.prepareStatement(query);

            for(int i = 0; i < params.length; i++)
                p.setString(i+1, params[i]);

            ResultSet rs = p.executeQuery();

            while(rs.next()) {
                Map<String,String> riga = new LinkedHashMap<>();

                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    riga.put(
                            rs.getMetaData().getColumnLabel(i),
                            rs.getString(i)
                    );
                }

                ris.add(riga);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ris;
    }

    public Map<String,String> row(String query, String... params){
        try {
            return rows(query, params).get(0);

        } catch(Exception e) {
            return null;
        }
    }

    public boolean update(String query, String... args) {
        boolean ris = true;

        try {
            PreparedStatement p = c.prepareStatement(query);

            for(int i = 0; i < args.length ;i++)
                p.setString(i+1, args[i]);

            p.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            ris = false;
        }

        return ris;
    }
}
