package com.andreaga.utility.entities;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Entity implements Cloneable {

    private int id;

    public Entity clone() {
        try {
            return (Entity) super.clone();
        }
        catch(CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Id : " + id + "\n" ;
    }

    public Map<String,String> toMap() {
        Map<String,String> ris = new HashMap<>();

        for(Method m : this.getClass().getMethods()) {
            if(m.getName().startsWith("get") || m.getName().startsWith("is")) {
                try {
                    String chiave;

                    if(m.getName().startsWith("get"))
                        chiave = m.getName().substring(3).toLowerCase();
                    else
                        chiave = m.getName().substring(2).toLowerCase();

                    if(chiave.equals("class"))
                        continue;

                    String valore = m.invoke(this).toString();
                    ris.put(chiave, valore);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return ris;
    }

    public void fromMap(Map<String,String> mappa) {
        for(Method m : this.getClass().getMethods()) {
            if(m.getName().startsWith("set") && m.getParameterCount() == 1) {
                String nome = m.getName().substring(3).toLowerCase();

                if(mappa.containsKey(nome) && mappa.get(nome) != null) {
                    String tipo = m.getParameters()[0].getType().getSimpleName().toLowerCase();

                    try {
                        switch(tipo) {
                            case "string":
                                m.invoke(this, mappa.get(nome));
                                break;
                            case "int":
                                m.invoke(this, Integer.parseInt(mappa.get(nome)));
                                break;
                            case "double":
                                m.invoke(this, Double.parseDouble(mappa.get(nome)));
                                break;
                            case "boolean":
                                m.invoke(this, mappa.get(nome).equalsIgnoreCase("true"));
                                break;
                        }
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String toXML() {
        String ris = "\n\t<" + getClass().getSimpleName().toLowerCase() +">";

        Map<String,String> dati = this.toMap();

        for(String key : dati.keySet())
            ris += "\n\t\t<" + key + ">" + dati.get(key) + "</" + key +">";

        ris += "\n\t</" + getClass().getSimpleName().toLowerCase() + ">";

        return ris;
    }

}
