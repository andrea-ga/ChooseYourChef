package com.andreaga.chooseyourchef.controllers;

import com.andreaga.chooseyourchef.dao.DAOpiatto;
import com.andreaga.chooseyourchef.entities.Piatto;
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
@RequestMapping("/piatti")
public class PiattoController {

    @Autowired
    private DAOpiatto dp;

    @Autowired
    private ApplicationContext ac;

    @GetMapping("/listapiatti")
    public String listapiatti(HttpSession session) {

        if(session.getAttribute("logincuoco") == null)
            return "redirect:/cuochi/formlogincuoco";


        return "listapiatti.html";
    }

    @GetMapping("/listapiattijson")
    @ResponseBody
    public String piattijson(@RequestParam Map<String,String> parametri,
                             HttpSession session) {
        String ris = "";

        Gson g = new Gson();

        if(session.getAttribute("logincuoco") != null)
            ris = g.toJson(dp.elencoPerId((String) session.getAttribute("logincuoco")));
        else
            ris = g.toJson(dp.elencoPerId(parametri.get("idcuoco")));

        return ris;
    }

    @GetMapping("/visualizzapiatti")
    public String visualizzapiatti() {
        return "visualizzapiatti.html";
    }

    @GetMapping("/formnuovopiatto")
    public String formnuovoPiatto() {
        return "formnuovopiatto.html";
    }

    @GetMapping("/nuovopiatto")
    public String nuovopiatto(@RequestParam Map<String,String> parametri,
                              HttpSession session) {
        Piatto p = ac.getBean(Piatto.class, parametri);
        p.setIdcuoco(Integer.parseInt((String) session.getAttribute("logincuoco")));

        if(dp.salva(p))
            return "redirect:/piatti/listapiatti";
        else
            return "errroreinserimento.html";
    }

    @GetMapping ("/eliminapiatto")
    public String eliminapiatto(@RequestParam Map<String,String> parametri) {
        if (dp.elimina(Integer.parseInt(parametri.get("idpiatto"))))
            return"redirect:/piatti/listapiatti";
        else
            return "erroreinserimento.html";
    }
}
