package com.andreaga.utility.db;

import com.andreaga.utility.entities.Entity;

import java.util.List;

public interface IDAO {

    public List<Entity> elenco();
    public boolean salva(Entity e);
    public boolean modifica(Entity e);
    public boolean elimina(int id);
}
