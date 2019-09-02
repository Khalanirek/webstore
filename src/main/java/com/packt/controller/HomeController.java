package com.packt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value="/")
    public String welcome(Model model){
        model.addAttribute("greeting", "Welcome to Web");
        model.addAttribute("tagline", "Super sklep");
        return "forward:/welcome/greeting";
    }

    @RequestMapping("/welcome/greeting")
    public String greeting(){
        return "welcome";
    }
}
