package com.textcomplexityanalyzer.analyzer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(/*@RequestParam(name="name", required=false, defaultValue="World") String name, */Model model) {
        //model.addAttribute("name", name);
        model.addAttribute("title", "Text complexity analyzer");
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Text complexity analyzer");
        return "about";
    }

}
