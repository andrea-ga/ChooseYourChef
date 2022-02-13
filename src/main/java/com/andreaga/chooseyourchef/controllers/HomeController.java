package com.andreaga.chooseyourchef.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller("/")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home.html";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.setAttribute("logincuoco",null);

        session.setAttribute("logincliente",null);

        return "redirect:/";
    }
}
