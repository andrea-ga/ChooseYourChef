package com.andreaga.chooseyourchef.controllers;

import com.andreaga.chooseyourchef.dao.DAOprenotato;
import com.andreaga.chooseyourchef.entities.Prenotato;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/prenotati")
public class PrenotatoController {

    @Autowired
    private DAOprenotato dp;

    @Autowired
    private ApplicationContext ac;

    @GetMapping("/formnuovaprenotazione")
    public String formnuovaPrenotazione() {
        return "formnuovaprenotazione.html";
    }

    @GetMapping("/nuovaprenotazione")
    public String nuovaprenotazione(@RequestParam Map<String,String> parametri,
                                    HttpSession session) {
        Prenotato p = ac.getBean(Prenotato.class, parametri);
        p.setPrenotazione((String) session.getAttribute("dataprenotazione"));
        p.setFasciaOraria((String) session.getAttribute("fasciaoraria"));

        if(dp.salva(p))
            return "redirect:/clienti/";
        else
            return "erroreinserimento.html";
    }

    @GetMapping("/idcliente")
    @ResponseBody
    public String prenotati(HttpSession session) {
        return (String) session.getAttribute("logincliente");
    }

    @GetMapping("/listaprenotazioni")
    public String listaprenotazioni(HttpSession session) {
        if(session.getAttribute("logincuoco") != null)
            return "listaprenotazionicuochi.html";
        else if(session.getAttribute("logincliente") != null)
            return "listaprenotazioniclienti.html";
        else
            return "redirect:/";
    }

    @GetMapping("/listaprenotazionijson")
    @ResponseBody
    public String prenotazionijson(HttpSession session) {
        String ris = "";

        Gson g = new Gson();
        if(session.getAttribute("logincuoco") != null)
            ris = g.toJson(dp.elencoPerIdCuoco((String) session.getAttribute("logincuoco")));
        else
            ris = g.toJson(dp.elencoPerIdCliente((String) session.getAttribute("logincliente")));

        return ris;
    }
}
