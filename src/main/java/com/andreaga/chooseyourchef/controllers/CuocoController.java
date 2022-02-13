package com.andreaga.chooseyourchef.controllers;

import com.andreaga.chooseyourchef.dao.DAOcuoco;
import com.andreaga.chooseyourchef.entities.Cuoco;
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
@RequestMapping("/cuochi")
public class CuocoController {

    @Autowired
    private DAOcuoco dc;

    @Autowired
    private ApplicationContext ac;

    @GetMapping("/formlogincuoco")
    public String formloginCuoco() {
        return "formlogincuoco.html";
    }

    @GetMapping("/")
    public String homeCuoco() {
        return "homecuoco.html";
    }

    @GetMapping("/logincuoco")
    public String loginCuoco(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) {
        if(dc.login(username, password)) {
            String id = dc.trovaId(username);
            session.setAttribute("logincuoco",id);

            if(session.getAttribute("logincliente") != null)
                session.setAttribute("logincliente", null);

            return "redirect:/cuochi/";
        } else {
            return "redirect:/cuochi/formlogincuoco";
        }
    }

    @GetMapping("/formnuovocuoco")
    public String formnuovoCuoco() {
        return "formnuovocuoco.html";
    }

    @GetMapping("/nuovocuoco")
    public String nuovocuoco( @RequestParam Map<String,String> parametri) {
        Cuoco c = ac.getBean(Cuoco.class, parametri);

        if(dc.salva(c))
            return "redirect:/";
        else
            return "erroreinserimento.html";
    }

    @GetMapping("/listacuochi")
    public String listacuochi(@RequestParam Map<String,String> parametri,
                              HttpSession session) {
        if(session.getAttribute("logincliente") == null)
            return "redirect:/clienti/formlogincliente";

        session.setAttribute("dataprenotazione", parametri.get("prenotazione"));
        session.setAttribute("fasciaoraria", parametri.get("fasciaoraria"));
        session.setAttribute("citta", parametri.get("citta"));

        return "listacuochi.html";
    }

    @GetMapping("/listacuochijson")
    @ResponseBody
    public String cuochijson(HttpSession session) {
        String ris = "";

        Gson g = new Gson();

        ris = g.toJson(dc.trovaDisponibilita((String) session.getAttribute("dataprenotazione"),
                (String) session.getAttribute("fasciaoraria"),
                (String) session.getAttribute("citta")));

        return ris;
    }

    @GetMapping("/visualizzacuoco")
    public String visualizzacuoco() {

        return "visualizzacuoco.html";
    }

    @GetMapping("/visualizzacuocojson")
    @ResponseBody
    public String cuocojson(@RequestParam Map<String,String> parametri) {
        String ris = "";

        Gson g = new Gson();

        ris = g.toJson(dc.trovaCuoco(parametri.get("idcuoco")));

        return ris;
    }
}
