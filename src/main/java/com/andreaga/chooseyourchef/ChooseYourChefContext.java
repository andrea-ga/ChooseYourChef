package com.andreaga.chooseyourchef;

import com.andreaga.chooseyourchef.dao.DAOcliente;
import com.andreaga.chooseyourchef.dao.DAOcuoco;
import com.andreaga.chooseyourchef.dao.DAOpiatto;
import com.andreaga.chooseyourchef.dao.DAOprenotato;
import com.andreaga.chooseyourchef.entities.Cliente;
import com.andreaga.chooseyourchef.entities.Cuoco;
import com.andreaga.chooseyourchef.entities.Piatto;
import com.andreaga.chooseyourchef.entities.Prenotato;
import com.andreaga.utility.db.Database;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Map;

@Configuration
public class ChooseYourChefContext {

    @Bean
    @Scope("singleton")
    public Database db() {
        Database ris = Database.getInstance();
        ris.connetti("chooseyourchef", "root", "Grh10!12");
        return ris;
    }

    @Bean
    public DAOcliente daoCliente() {
        return new DAOcliente();
    }

    @Bean
    public DAOcuoco daoCuoco() {
        return new DAOcuoco();
    }

    @Bean
    public DAOprenotato daoPrenotato() {
        return new DAOprenotato();
    }

    @Bean
    public DAOpiatto daoPiatto() {
        return new DAOpiatto();
    }

    @Bean
    @Scope("prototype")
    public Cliente clientevuoto() {
        return new Cliente();
    }

    @Bean
    @Scope("prototype")
    @Primary
    public Cliente clienteMappa(Map<String,String> m) {
        Cliente c = new Cliente();
        c.fromMap(m);
        return c;
    }

    @Bean
    @Scope("prototype")
    public Cuoco cuocovuoto() {
        return new Cuoco();
    }

    @Bean
    @Scope("prototype")
    @Primary
    public Cuoco cuocoMappa(Map<String,String> m) {
        Cuoco c = new Cuoco();
        c.fromMap(m);

        return c;
    }

    @Bean
    @Scope("prototype")
    public Prenotato prenotatovuoto() {
        return new Prenotato();
    }

    @Bean
    @Scope("prototype")
    @Primary
    public Prenotato prenotatoMappa(Map<String,String> m) {
        Prenotato p = new Prenotato();
        p.fromMap(m);
        return p;
    }

    @Bean
    @Scope("prototype")
    public Piatto piattovuoto() {
        return new Piatto();
    }

    @Bean
    @Scope("prototype")
    @Primary
    public Piatto piattoMappa(Map<String,String> m) {
        Piatto p = new Piatto();
        p.fromMap(m);
        return p;
    }
}
