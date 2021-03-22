package com.textcomplexityanalyzer.analyzer.controllers;


import com.textcomplexityanalyzer.analyzer.models.Text;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Value("${upload.path")
    private String uploadPath;

    public Text text = new Text();

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("text", text.getText());
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("text", text.getText());
        return "about";
    }

    @PostMapping("/")
    public String analyze(@RequestParam String userText, Model model) {
        text = new Text(userText);
        model.addAttribute("text", text.getText());
        return "main";
    }


}
