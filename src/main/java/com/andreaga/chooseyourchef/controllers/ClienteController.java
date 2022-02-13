package com.andreaga.chooseyourchef.controllers;

import com.andreaga.chooseyourchef.dao.DAOcliente;
import com.andreaga.chooseyourchef.entities.Cliente;
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
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private DAOcliente dc;

    @Autowired
    private ApplicationContext ac;

    @GetMapping("/formlogincliente")
    public String formloginCliente() {
        return "formlogincliente.html";
    }

    @GetMapping("/")
    public String homeCliente() {
        return "homecliente.html";
    }

    @GetMapping("/logincliente")
    public String loginCliente(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session) {
        if(dc.login(username, password)) {
            String id = dc.trovaId(username);
            session.setAttribute("logincliente",id);

            if(session.getAttribute("logincuoco") != null)
                session.setAttribute("logincuoco", null);

            return "redirect:/clienti/";
        } else {
            return "redirect:/clienti/formlogincliente";
        }
    }

    @GetMapping("/formnuovocliente")
    public String formnuovoCliente() {
        return "formnuovocliente.html";
    }

    @GetMapping("/nuovocliente")
    public String nuovocliente( @RequestParam Map<String,String> parametri) {
        Cliente c = ac.getBean(Cliente.class, parametri);

        if(dc.salva(c))
            return "redirect:/";
        else
            return "erroreinserimento.html";
    }

    @GetMapping("/visualizzacliente")
    public String visualizzacliente() {

        return "visualizzacliente.html";
    }

    @GetMapping("/visualizzaclientejson")
    @ResponseBody
    public String clientejson(@RequestParam Map<String,String> parametri) {
        String ris = "";

        Gson g = new Gson();

        ris = g.toJson(dc.trovaCliente(parametri.get("idcliente")));

        return ris;
    }
}
