package com.textcomplexityanalyzer.analyzer.controllers;


import com.textcomplexityanalyzer.analyzer.core.exceptions.DocumentNotCreatedException;
import com.textcomplexityanalyzer.analyzer.models.Text;
import com.textcomplexityanalyzer.analyzer.services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private TextService textService;

    public Text text = new Text();

    @GetMapping("/")
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @PostMapping("/")
    public String analyze(@RequestParam String userText, Model model, String fac, String wcc, String vtc, String n,
                          String aa, String pn, String og) throws DocumentNotCreatedException {
        if (userText.length() != 0)
        text = textService.createText(userText);
        model.addAttribute("text", userText);
        model.addAttribute("fac", fac);
        model.addAttribute("wcc", wcc);
        model.addAttribute("vtc", vtc);
        model.addAttribute("n", n);
        model.addAttribute("aa", aa);
        model.addAttribute("pn", pn);
        model.addAttribute("og", og);
        model.addAttribute("FACnum", text.getStatistics().get("FiniteAdverbalCl").toString());
        model.addAttribute("WCCnum", text.getStatistics().get("WhComplementClauses").toString());
        model.addAttribute("VTCnum", text.getStatistics().get("VerbThatClauses").toString());

        model.addAttribute("Nnum", text.getStatistics().get("Nouns").toString());
        model.addAttribute("AAnum", text.getStatistics().get("AttributiveAdjectives").toString());
        model.addAttribute("PNnum", text.getStatistics().get("PremodifyingNouns").toString());
        model.addAttribute("OGnum", text.getStatistics().get("OfGenitives").toString());
        return "main";
    }


}
