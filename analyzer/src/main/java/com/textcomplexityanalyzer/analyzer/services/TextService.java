package com.textcomplexityanalyzer.analyzer.services;

import com.textcomplexityanalyzer.analyzer.core.exceptions.DocumentNotCreatedException;
import com.textcomplexityanalyzer.analyzer.models.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextService {
    @Autowired
    private GateService gateService;

    public Text currText = new Text();

    public Text createText(String txt) throws DocumentNotCreatedException {
        var text = gateService.processWithGate(txt);
        currText = text;
        return currText;
    }
}
