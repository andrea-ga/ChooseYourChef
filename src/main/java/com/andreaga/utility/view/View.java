package com.andreaga.utility.view;

import com.andreaga.utility.entities.Entity;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {

    private final String percorsoCartella;

    public View(String percorsoCartella) {
        this.percorsoCartella = percorsoCartella;
    }

    public String leggi(String nomeFile) {
        String ris = "";

        try {
            Scanner dati = new Scanner(new File(percorsoCartella + "/" + nomeFile));
            while(dati.hasNextLine())
                ris += dati.nextLine();
            dati.close();
        } catch(Exception e) {
            e.printStackTrace();
            ris = "Problemi nella lettura del file" + nomeFile;
        }

        return ris;
    }

    public String grafica(String nomeFile, Entity e) {
        String ris = leggi(nomeFile);

        Map<String,String> campi = e.toMap();

        for(String k : campi.keySet())
            ris = ris.replace("[" + k + "]", campi.get(k));

        return ris;
    }

    public String grafica(String nomeFile, List<Entity> entities) {
        String temp = leggi(nomeFile);
        String ris = "";

        for(Entity e : entities) {
            ris += temp;

            Map<String,String> campi = e.toMap();

            for(String k : campi.keySet())
                ris = ris.replace("[" + k + "]", campi.get(k));
        }

        return ris;
    }
}

